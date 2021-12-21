package ca.ntro.core.task_graphs.executable_task_graph;

import org.junit.Before;

import ca.ntro.core.initialization.InitializerTestJdk;

public class ExecutableTaskGraphTestsJdk extends ExecutableTaskGraphTests {

	@Before
	public void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
