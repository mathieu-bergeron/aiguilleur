package ca.ntro.core.graphs.tests;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class HierarchicalGraphTestsJdk extends HierarchicalGraphTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
