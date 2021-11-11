package ca.ntro.jj.identifyers;

import org.junit.BeforeClass;

import ca.ntro.jj.initialization.InitializerTestJdk;

public class PathTestJdk extends PathTest {
	
	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
