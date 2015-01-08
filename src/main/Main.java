package main;

import graph.IGraph;
import graphcomputer.GraphComputer;
import it.unimi.dsi.fastutil.ints.Int2IntMap;

import java.time.Clock;

import measures.Modularity;
import measures.WeightedModularity;
import persistence.GraphReader;

import communitydetection.LP;

import dijkstra.Dijkstra;

public class Main {
	public static void main(String[] args) {
//		String fname = "data/dblp.txt";
//		IGraph g = GraphReader.readGraph(fname, "\t", false, false);
		
		String fname = "data/karate.dat";
		IGraph g = GraphReader.readGraph(fname, " ", false, false);
		
		System.out.println("Tot nodes: "+g.getNodeCount());
		
//		oldplp(g);
		plp(g);
//		dijkstra(g);
	}
	
	protected static void plp(IGraph g) {
		Clock clock = Clock.systemDefaultZone();
		GraphComputer computer = new GraphComputer();

		computer.setVertexFunction(new LP(g));
		long start = clock.millis();
		computer.execute(g);
		long end = clock.millis();
		System.out.println("PLP in "+(end - start) / 1000.0+" s");
		computer.dismiss();
		Int2IntMap nodeToCommunity = computer.getResult();

		Modularity wm = new WeightedModularity(g, nodeToCommunity);
		wm.execute();
		System.out.println("Modularity: "+wm.getValue());
	}
	
//	protected static void oldplp(IGraph g) {
//		Clock clock = Clock.systemDefaultZone();
//		long start = clock.millis();
//		PLP plp = new PLP(g, 20);
//		plp.execute();
//		long end = clock.millis();
//		System.out.println("PLP in "+(end - start) / 1000.0+" s");
//		Int2IntMap nodeToCommunity = plp.getResult();
//
////		nodeToCommunity.keySet().forEach(k -> System.out.println(k+" -> "+nodeToCommunity.get(k)));
//		
////		Modularity m = new Modularity(g, nodeToCommunity);
////		m.execute();
////		System.out.println(m.getValue());
//		
//		Modularity wm = new WeightedModularity(g, nodeToCommunity);
//		wm.execute();
//		System.out.println("Modularity: "+wm.getValue());
//	}
	
	protected static void dijkstra(IGraph g) {
		Clock clock = Clock.systemDefaultZone();
		long start = clock.millis();
		Dijkstra dijkstra = new Dijkstra(g);
		int n=0;
		for(int src : g.getEdgeSet()) {
			n += 1;
			if( n % 2 == 0 )
				System.out.println(n);
			dijkstra.execute(src);
			System.out.println(dijkstra.getResult());
		}
//		System.out.println("Finished executing");
//		Paths result = dijkstra.getResult();
//		System.out.println(result);
		long end = clock.millis();
		System.out.println("Dijkstra in "+(end - start) / 1000.0+" s");
	}
	
}
