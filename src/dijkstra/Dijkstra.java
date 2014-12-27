package dijkstra;

import exceptions.NonNegativeWeightsRequired;
import graph.IGraph;

public class Dijkstra {

	public Dijkstra(IGraph g) {
		int edgeId, src, dst;
		float weight;
		for( int[] e : g.getEdgeIterable() ) {
			edgeId = e[0];
			if( g.getEdgeWeight(edgeId) < 0 ) {
				src = e[1]; dst = e[2]; weight = g.getEdgeWeight(edgeId);
				throw new NonNegativeWeightsRequired(edgeId, src, dst, weight);
			}
		}
	}
	
}
