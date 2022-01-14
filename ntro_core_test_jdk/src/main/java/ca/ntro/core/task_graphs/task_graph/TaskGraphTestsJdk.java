package ca.ntro.core.task_graphs.task_graph;

import org.junit.Before;

import ca.ntro.core.initialization.InitializerTestJdk;

public class TaskGraphTestsJdk extends TaskGraphTests {

	@Before
	public void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
