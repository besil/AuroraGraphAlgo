package graphcomputer;

import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2IntMap;

import java.util.concurrent.ForkJoinPool;

import communitydetection.VertexFunction;

public class GraphComputer {
	protected final ForkJoinPool pool;
	protected VertexFunction vertexFunction = null;
	
	public GraphComputer() {
		this.pool	= new ForkJoinPool(Runtime.getRuntime().availableProcessors());
	}
	
	public void execute(final IGraph graph) {
		this.execute(graph, 20);
	}
	
	/**
	 * Execute algorithm 
	 * @param graph
	 * @param iterations
	 */
	public void execute(final IGraph graph, int iterations) {
		if(vertexFunction == null) {
			throw new VertexFunctionNotFound();
		}
		
		for(int it=0; it<iterations; it++) {
			try {
				pool.submit( () -> {
					graph.getNodeSet().parallelStream()
						.forEach( node ->
							this.vertexFunction.apply(graph, node)
						);
				} ).get();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Int2IntMap getResult() {
		return this.vertexFunction.getResult();
	}
	
	public void dismiss() {
		this.pool.shutdown();
	}
	
	public VertexFunction getVertexFunction() {
		return vertexFunction;
	}
	
	public void setVertexFunction(VertexFunction vertexFunction) {
		this.vertexFunction = vertexFunction;
	}
	
}
