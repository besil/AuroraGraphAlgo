package graphcomputer;

import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2DoubleMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;

import java.util.concurrent.ForkJoinPool;

import centrality.ConnectedComponents;
import centrality.PageRank;
import communitydetection.LP;

public class GraphComputer {
	protected final ForkJoinPool pool;
	
	public GraphComputer() {
		this( Runtime.getRuntime().availableProcessors() );
	}
	
	public GraphComputer(int nthreads) {
		this.pool 	= new ForkJoinPool(nthreads);
	}
	
	/**
	 * Execute algorithm 
	 * @param graph
	 * @param iterations
	 */
	public void execute(final VertexFunction vertexFunction, final IGraph graph, int iterations) {
		
		for(int it=0; it<iterations; it++) {
			try {
				pool.submit( () -> {
					graph.getNodeSet().parallelStream()
						.forEach( node ->
							vertexFunction.apply(graph, node)
						);
				} ).get();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void dismiss() {
		this.pool.shutdown();
	}
	
	public Int2IntMap getResult(LP lp) {
		return lp.getResult();
	}
	
	public Int2DoubleMap getResult(PageRank pg) {
		return pg.getResult();
	}

	public Int2IntMap getResult(ConnectedComponents cc) {
		return cc.getResult();
	}
}
