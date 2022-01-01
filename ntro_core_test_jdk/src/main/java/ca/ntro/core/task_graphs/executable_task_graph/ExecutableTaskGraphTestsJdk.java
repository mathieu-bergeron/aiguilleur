package ca.ntro.core.task_graphs.executable_task_graph;

import org.junit.Before;

import ca.ntro.core.initialization.InitializerTestJdk;
import ca.ntro.core.task_graphs.task_graph.TaskGraphTests;

public class ExecutableTaskGraphTestsJdk extends TaskGraphTests {

	@Before
	public void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
