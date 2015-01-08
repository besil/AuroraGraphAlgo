package centrality;

import graph.IGraph;
import graphcomputer.VertexFunction;
import it.unimi.dsi.fastutil.ints.Int2DoubleMap;
import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;

public class PageRank extends VertexFunction {
	protected Int2DoubleMap result;
	protected final int totNodes;
	protected final double dumpingFactor;
	protected final double dumper;
	
	public PageRank(IGraph g) {
		totNodes = g.getNodeCount();
		dumpingFactor = 0.85;
		dumper = (1-dumpingFactor) / totNodes;
		
		this.result = new Int2DoubleOpenHashMap(totNodes);
		for(int n : g.getNodeSet())
			this.result.put(n, 1.0 / totNodes);
	}
	
	@Override
	public void apply(IGraph graph, int n) {
		double pr = 0.0;
		for( int pj : graph.getInNeighbours(n) )
			pr += result.get(pj) / graph.wOutDegree(pj);
		double rank = this.dumper + this.dumpingFactor * pr;
		result.put( n, rank );
	}

	@Override
	public Int2DoubleMap getResult() {
		return result;
	}

}
