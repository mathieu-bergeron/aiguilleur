package ca.ntro.core.graphs.graph;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class GraphTestsJdk extends GraphTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
