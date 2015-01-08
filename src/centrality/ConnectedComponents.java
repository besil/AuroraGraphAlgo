package centrality;

import graph.IGraph;
import graphcomputer.VertexFunction;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;

public class ConnectedComponents extends VertexFunction {
	protected Int2IntMap nodeToComponent;
	
	public ConnectedComponents(IGraph g) {
		nodeToComponent = new Int2IntOpenHashMap();
		g.getNodeSet().stream().forEach(n -> nodeToComponent.put(n, n));
	}

	@Override
	public void apply(IGraph graph, int n) {
		int currLabel = nodeToComponent.get(n);
		int nLabel;
		for(int v : graph.getNeighbours(n)) {
			nLabel = nodeToComponent.get(v);
			if( nLabel < currLabel )
				nodeToComponent.put(n, nLabel);
		}
	}

	@Override
	public Int2IntMap getResult() {
		return nodeToComponent;
	}

}
