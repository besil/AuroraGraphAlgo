package dijkstra;

import it.unimi.dsi.fastutil.ints.IntList;

public class Path {
	protected final int src;
	protected float pathWeight;
	protected IntList nodes;
	
	public Path(int src, IntList n, float f) {
		this.src = src;
		this.nodes = n;
		this.pathWeight = f;
	}

	public IntList getNodes() {
		return nodes;
	}
	
	public float getPathWeight() {
		return pathWeight;
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
