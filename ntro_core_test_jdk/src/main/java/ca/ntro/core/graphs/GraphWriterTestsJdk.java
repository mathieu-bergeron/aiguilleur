package ca.ntro.core.graphs;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class GraphWriterTestsJdk extends GraphWriterTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
