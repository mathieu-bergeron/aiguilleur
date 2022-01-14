package ca.ntro.core.object_diff;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class ObjectDiffTestsJdk extends ObjectDiffTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
