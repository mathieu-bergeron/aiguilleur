package ca.ntro.core.graphs.tests;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class DagTestsJdk extends DagTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
