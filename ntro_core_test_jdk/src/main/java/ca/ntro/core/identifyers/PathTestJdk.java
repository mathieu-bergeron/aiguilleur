package ca.ntro.core.identifyers;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class PathTestJdk extends PathTest {
	
	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
