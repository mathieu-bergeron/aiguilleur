package ca.ntro.core.edit_distance;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class EditDistanceTestsJdk extends EditDistanceTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
