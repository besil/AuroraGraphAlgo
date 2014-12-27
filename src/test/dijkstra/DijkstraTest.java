package test.dijkstra;

import static org.junit.Assert.*;
import graph.mutable.Graph;
import graph.mutable.implementations.adl.AdlDirectGraph;

import org.junit.Before;
import org.junit.Test;

import dijkstra.Dijkstra;
import exceptions.NonNegativeWeightsRequired;

public class DijkstraTest {
	
	@Before
	public void setUp() {
//		g.getEdgeSet().forEach(action);
	}
	
	@Test
	public void NonNegativeWeights() {
		Graph g = new AdlDirectGraph();
		int eedgeId = 0;
		int esrc = 0;
		int edst = 1;
		float eweight = -2;
		
		g.addEdge(0, 1, -2);
		g.addEdge(0, 2, 3);
		
		try {
			new Dijkstra(g);
			fail("Should have thrown NonNegativeWeightsRequired(0, 0, 1, -2)");
		} catch(NonNegativeWeightsRequired nnwr) {
			int fsrc = nnwr.getSrc();
			int fdst = nnwr.getDst();
			int fedgeId = nnwr.getEdgeId();
			float fweight = nnwr.getWeight();
			
			assertEquals(eedgeId, fedgeId);
			assertEquals(esrc, fsrc);
			assertEquals(edst, fdst);
			assertEquals(eweight, fweight, 0.0);
		}
	}

}
