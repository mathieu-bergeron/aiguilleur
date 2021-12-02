package ca.ntro.core.services;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class CollectionsTestsJdk extends CollectionsTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
