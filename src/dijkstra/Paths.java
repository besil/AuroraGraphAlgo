package dijkstra;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import java.util.Iterator;

public class Paths implements Iterable<Path> {
	protected final int src;
	protected final Int2ObjectMap<Path> nodeToPath;
	
	public Paths(int src, Int2ObjectMap<Path> paths) {
		super();
		this.src = src;
		this.nodeToPath = paths;
	}

	public int getSrcNode() {
		return src;
	}
	
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
}
