package ca.ntro.core.graphs.hierarchical_graph;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class HierarchicalGraphTestsJdk extends HierarchicalGraphTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
