package main;

import graph.IGraph;
import it.unimi.dsi.fastutil.ints.Int2IntMap;

import java.time.Clock;

import measures.Modularity;
import measures.WeightedModularity;
import persistence.GraphReader;

import communitydetection.PLP;

public class Main {
	public static void main(String[] args) {
//		String fname = "data/callfreq.csv";
//		IGraph g = GraphReader.readGraph(fname, ",", true, true);
		
		String fname = "data/dblp.txt";
		IGraph g = GraphReader.readGraph(fname, "\t", false, false);
		
//		String fname = "data/karate.dat";
//		IGraph g = GraphReader.readGraph(fname, " ", false, false);
		
		System.out.println(g.getNodeCount());
		
		Clock clock = Clock.systemDefaultZone();
		long start = clock.millis();
		PLP plp = new PLP(g, 20);
		plp.execute();
		long end = clock.millis();
		System.out.println("PLP in "+(end - start) / 1000.0+" s");
		Int2IntMap nodeToCommunity = plp.getResult();

//		nodeToCommunity.keySet().forEach(k -> System.out.println(k+" -> "+nodeToCommunity.get(k)));
		
//		System.out.println(nodeToCommunity);
//		Modularity m = new Modularity(g, nodeToCommunity);
		Modularity m = new WeightedModularity(g, nodeToCommunity);
		m.execute();
		System.out.println(m.getValue());
	}
}
