package ca.ntro.core.graphs.directed_graph;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class DirectedGraphTestsJdk extends DirectedGraphTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
