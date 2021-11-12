package ca.ntro.core.identifyers.matchers;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class PathMatcherTestsJdk extends PathMatcherTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
