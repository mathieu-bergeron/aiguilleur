package ca.ntro.core.reflection;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;
import ca.ntro.core.reflection.object_graph.ObjectGraphTests;

public class ObjectGraphTestsJdk extends ObjectGraphTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}
}
