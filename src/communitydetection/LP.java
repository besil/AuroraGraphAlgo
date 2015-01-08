package communitydetection;

import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2FloatMap;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;

public class LP extends VertexFunction {
	protected Int2IntMap result;
	
	public LP(IGraph g) {
		this.result = new Int2IntOpenHashMap(g.getNodeCount());
		g.getNodeSet().stream().forEach(n -> result.put(n, n));
	}
	
	@Override
	public void apply(final IGraph g, int n) {
		int newLabel = this.getNewLabel(g, n);
		this.result.put(n, newLabel);
	}

	private int getNewLabel(IGraph g, int n) {
		if( g.degree(n) > 0 ) {
			Int2FloatMap commMap = new Int2FloatOpenHashMap(); 
			int neighLabel = -1, maxLabel = result.get(n);
			int edgeId = -1;
			float oldVal, weight, maxWeight=0.0f;
			IntSet inNeighs = g.getInNeighbours(n);

			for( int inNeigh : inNeighs ) {
				neighLabel = result.get(inNeigh);
				oldVal = commMap.containsKey(neighLabel) ? commMap.get(neighLabel) : 0.0f;
				edgeId = g.getEdgeBetween(inNeigh, n);
				weight = g.getEdgeWeight( edgeId );
				commMap.put( neighLabel, oldVal + weight );

				if( commMap.get(neighLabel) > maxWeight ) {
					maxLabel 	= neighLabel;
					maxWeight 	= commMap.get(neighLabel);
				}
			}
			return maxLabel;
		}
		return n;
	}

	@Override
	public Int2IntMap getResult() {
		return result;
	}
	
}
