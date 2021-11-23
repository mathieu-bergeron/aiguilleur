package ca.ntro.core.reflection;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class ObjectGraphTestsJdk extends ObjectGraphTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}
}
