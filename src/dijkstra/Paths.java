package dijkstra;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import java.util.Iterator;

/**
 * An object representing all paths from src node to all other node in the graph.
 * Each path is represented by class Path.
 * @author besil
 *
 */
public class Paths implements Iterable<Path> {
	protected final int src;
	protected final Int2ObjectMap<Path> nodeToPath;
	
	public Paths(int src, Int2ObjectMap<Path> paths) {
		super();
		this.src = src;
		this.nodeToPath = paths;
	}

	/**
	 * Returns the src node
	 * @return
	 */
	public int getSrcNode() {
		return src;
	}
	
	/**
	 * Returns a map from nodeId to Path object.
	 * NodeId is the destination node of the associated Path object.
	 * @return
	 */
	public Int2ObjectMap<Path> getNodeToPath() {
		return nodeToPath;
	}

	@Override
	public Iterator<Path> iterator() {
		return nodeToPath.values().iterator();
	}

	/**
	 * Return the number of possible paths
	 * @return
	 */
	public int size() {
		return this.nodeToPath.size();
	}
	
	@Override
	public String toString() {
		String res = "Source Node: "+this.src +"\n";
		for(int d : this.nodeToPath.keySet()) {
			res += "dst: "+d+". Path: "+nodeToPath.get(d)+"\n";
		}
		return res.substring(0, res.length()-1);
	}
}
