package graphcomputer;

import graph.IGraph;

public abstract class VertexFunction {
	public abstract void apply(IGraph graph, int n);
	
	public abstract Object getResult();
}
