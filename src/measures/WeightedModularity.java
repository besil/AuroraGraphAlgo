package measures;

import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2IntMap;

public class WeightedModularity extends Modularity {

	public WeightedModularity(IGraph g, Int2IntMap nodeToCommunity) {
		super(g, nodeToCommunity);
	}
	
	@Override
	protected double getDivisor(IGraph g) {
		return g.getEdgeSet().parallelStream()
			.mapToDouble(e -> g.getEdgeWeight(e))
			.reduce((w0, w1) -> w0 + w1).getAsDouble();
	}

}
