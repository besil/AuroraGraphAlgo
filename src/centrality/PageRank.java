package centrality;

import graph.IGraph;
import graphcomputer.VertexFunction;
import it.unimi.dsi.fastutil.ints.Int2DoubleMap;
import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;

public class PageRank extends VertexFunction {
	protected Int2DoubleMap result;
	
	public PageRank(IGraph g) {
		final int totNodes = g.getNodeCount();
		this.result = new Int2DoubleOpenHashMap(totNodes);
		for(int n : g.getNodeSet())
			this.result.put(n, 1.0 / totNodes);
	}
	
	@Override
	public void apply(IGraph graph, int n) {

	}

	@Override
	public Int2DoubleMap getResult() {
		return result;
	}

}
