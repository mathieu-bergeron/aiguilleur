package ca.ntro.core.task_graphs.task_graph;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class TaskGraphTestsJdk extends TaskGraphTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
