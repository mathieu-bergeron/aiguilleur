package ca.ntro.core.stream;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class StreamTestsJdk extends StreamTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
