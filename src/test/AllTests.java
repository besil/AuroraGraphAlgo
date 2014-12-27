package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.dijkstra.DijkstraTest;

@RunWith(Suite.class)
@SuiteClasses({DijkstraTest.class})
public class AllTests {

}
