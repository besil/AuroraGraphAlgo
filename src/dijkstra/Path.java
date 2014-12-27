package dijkstra;

import it.unimi.dsi.fastutil.ints.IntList;

/**
 * Represents a path from node src to a given node.
 * @author besil
 *
 */
public class Path {
	protected final int src;
	protected float pathWeight;
	protected IntList nodes;
	
	public Path(int src, IntList n, float f) {
		this.src = src;
		this.nodes = n;
		this.pathWeight = f;
	}

	/**
	 * Returns the path of (minimum) weight pathWeight from src node.
	 * @return
	 */
	public IntList getNodes() {
		return nodes;
	}
	
	/**
	 * Returns the weight of the path
	 * @return
	 */
	public float getPathWeight() {
		return pathWeight;
	}
	
	/**
	 * Returns the destination node of the path
	 * @return
	 */
	public int getDestination() {
		return nodes.getInt(nodes.size()-1);
	}
	
	@Override
	public String toString() {
		String res = "["+src+"] -> ";
		
		for(int v : nodes) {
			res += v+" -> ";
		}
		res = res.substring(0, res.length()-3);
		
		res += ": "+pathWeight;
		
		return res;
	}
}
