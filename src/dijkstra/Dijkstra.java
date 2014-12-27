package dijkstra;

import exceptions.NonNegativeWeightsRequired;
import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2FloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;

public class Dijkstra {
	protected int src;
	protected IntSet d;
	protected Int2FloatMap c;
	protected Int2IntMap p;
	protected IGraph g;
	
	public Dijkstra(IGraph g) {
		this.g = g;
		
		int edgeId, src, dst;
		float weight;
		for( int[] e : g.getEdgeIterable() ) {
			edgeId = e[0];
			if( g.getEdgeWeight(edgeId) < 0 ) {
				src = e[1]; dst = e[2]; weight = g.getEdgeWeight(edgeId);
				throw new NonNegativeWeightsRequired(edgeId, src, dst, weight);
			}
		}
	}
	
	/**
	 * Compute all paths from node src to every other node in the given graph
	 * @param src
	 */
	public void execute(int src) {
		this.src = src;
		this.d = new IntOpenHashSet();
		this.c = new Int2FloatOpenHashMap();
		this.p = new Int2IntOpenHashMap();
		
		d.add(src);
		for( int v : g.getNodeSet() ) {
			c.put(v, Float.POSITIVE_INFINITY);
			p.put(v, -1);
		}
		c.put(src, 0f);
		
		while(!d.isEmpty()) {
			int v = this.getMin(d, c);
			d.remove(v);
			for( int u : g.getOutNeighbours(v) ) {
				float wvu = g.getEdgeWeight( g.getEdgeBetween(v, u) );
				if( c.get(v) + wvu < c.get(u) ) {
					if( c.get(u) == Float.POSITIVE_INFINITY ) {
						d.add(u);
					}
					c.put(u, c.get(v) + wvu);
					p.put(u, v);
				}
			}
		}
	}
	
	private int getMin(final IntSet d, final Int2FloatMap c) {
		int minV = -1;
		float minDist = Float.POSITIVE_INFINITY;
		for( int v : d ) {
			if( c.get(v) <= minDist ) {
				minV = v;
				minDist = c.get(v);
			}
		}
		return minV;
	}

	/**
	 * Returns a Paths object, representing the result
	 * @return
	 */
	public Paths getResult() {
//		System.out.println("----- C ------");
//		System.out.println(c);
//		
//		System.out.println("----- D ------");
//		System.out.println(d);
//		
//		System.out.println("----- P ------");
//		System.out.println(p);
		
		Int2ObjectMap<Path> nodeToPath = new Int2ObjectOpenHashMap<>();
		for( int v : g.getNodeSet() ) {
			if( v != this.src ) {
				nodeToPath.put(v, buildPath(v));
			}
		}
//		for(Path p : nodeToPath.values())
//			System.out.println(p);
		return new Paths(src, nodeToPath);
	}
	
	protected Path buildPath(final int dst) {
		int v = dst;
		IntList nodes = new IntArrayList();
		
		while( v != this.src ) {
			nodes.add(v);
			v = this.p.get(v);
		}
		
		return new Path(this.src, reverse(nodes), c.get(dst));
	}

	private IntList reverse(IntList nodes) {
		IntList reversed = new IntArrayList(nodes.size());
		for(int i=nodes.size()-1; i>=0; i--) {
			reversed.add(nodes.get(i));
		}
		return reversed;
	}

}