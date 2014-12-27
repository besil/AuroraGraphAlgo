package test.dijkstra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import graph.IGraph;
import graph.mutable.Graph;
import graph.mutable.implementations.adl.AdlDirectGraph;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import testing.graph.GraphFactory;
import dijkstra.Dijkstra;
import dijkstra.Path;
import dijkstra.Paths;
import exceptions.NonNegativeWeightsRequired;

public class DijkstraTest {
	protected IGraph g;

	@Before
	public void setUp() {
		this.g = GraphFactory.buildGraph(new AdlDirectGraph());
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

	@Test
	public void testPaths() {
		Dijkstra dijkstra = new Dijkstra(this.g);
		int srcNode = 0;
		dijkstra.execute(srcNode);
		Paths paths = dijkstra.getResult();

		assertEquals( srcNode, paths.getSrcNode());
		
		final int numberOfPaths = 5;
		assertEquals( numberOfPaths, paths.size() );
		
		for(Entry<Integer, Path> nodeToPath : paths.getNodeToPath().entrySet()) {
			int node = nodeToPath.getKey();
			Path path = nodeToPath.getValue();
			IntList nodes = path.getNodes();
			
			float expectedWeight 	= node <= 1 ? node : node + 2f;
			int expectedPathLength 	= node;
			
			assertEquals( expectedWeight, path.getPathWeight(), 0.0 );
			assertEquals( expectedPathLength, nodes.size() );
			if( node > 0 ) {
				for( int i=1; i<node; i++ ) {
					assertEquals(true, nodes.contains(i) );
				}
			}
		}
	}
}
