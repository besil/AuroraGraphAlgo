package graphcomputer;

import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2IntMap;

import java.util.concurrent.ForkJoinPool;

import communitydetection.VertexFunction;

public class GraphComputer {
	protected final ForkJoinPool pool;
	protected VertexFunction vertexFunction;
	
	public GraphComputer() {
		this.pool	= new ForkJoinPool(Runtime.getRuntime().availableProcessors());
	}
	
	public void execute(final IGraph graph) {
		this.execute(graph, 20);
	}
	
	public void execute(final IGraph graph, int iterations) {
		for(int it=0; it<iterations; it++) {
			pool.submit( () -> {
				graph.getNodeSet().parallelStream()
					.forEach( node ->
						this.vertexFunction.apply(graph, node)
					);
				return 0; 
			} );
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
