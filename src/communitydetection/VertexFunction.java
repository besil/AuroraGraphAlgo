package communitydetection;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import graph.IGraph;

public abstract class VertexFunction {
	public abstract void apply(IGraph graph, int n);

	public abstract Int2IntMap getResult();
	
}
