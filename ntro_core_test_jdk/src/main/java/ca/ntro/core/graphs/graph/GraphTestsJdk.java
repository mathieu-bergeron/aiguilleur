package ca.ntro.core.graphs.graph;

import org.junit.Before;

import ca.ntro.core.initialization.InitializerTestJdk;

public class GraphTestsJdk extends GraphTests {

	@Before
	public void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
