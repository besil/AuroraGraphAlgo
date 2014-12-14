package communitydetection;

import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2FloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;

public class PLP {
	protected final IGraph g;
	protected final int iterations;
	protected Int2IntMap result;
	protected IntSet selectedNodes;

	public PLP(IGraph g, int iterations, Int2IntMap initialPartition, IntSet forNodes) {
		super();
		this.g = g;
		this.iterations = iterations;
		this.result = initialPartition;
		this.selectedNodes = forNodes;
	}
	
	public PLP(IGraph g, int iterations) {
		this(g, iterations, new Int2IntOpenHashMap(g.getNodeCount()), g.getNodeSet() );
		g.getNodeSet().parallelStream()
			.forEach(n -> result.put(n, n));
	}

	public void execute() {
		for(int it=0; it<this.iterations; it++) {
//			System.out.println("Iteration "+it);
			this.selectedNodes.parallelStream()
			.forEach(n -> {
				int newLabel = this.getNewLabel(n);
				result.put((int)n, newLabel);
			});
		}
	}

	private int getNewLabel(int n) {
		if( g.degree(n) > 0 ) {
			Int2FloatMap commMap = new Int2FloatOpenHashMap(); 
			int neighLabel = -1, maxLabel = result.get(n);
			int edgeId = -1;
			float oldVal, weight, maxWeight=0.0f;
			IntSet inNeighs = g.getInNeighbours(n);

			for( int inNeigh : inNeighs ) {
				neighLabel = result.get(inNeigh);
				oldVal = commMap.containsKey(neighLabel) ? commMap.get(neighLabel) : 0.0f;
				edgeId = g.getEdgeBetween(inNeigh, n);
				weight = g.getEdgeWeight( edgeId );
				commMap.put( neighLabel, oldVal + weight );

				if( commMap.get(neighLabel) > maxWeight ) {
					maxLabel 	= neighLabel;
					maxWeight 	= commMap.get(neighLabel);
				}
			}
			return maxLabel;
		}
		return n;
	}

	public Int2IntMap getResult() {
		return result;
	}
}
