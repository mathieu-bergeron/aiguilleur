package ca.ntro.core.task_graphs.executable_task_graph;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;

public class ExecutableTaskGraphTestsJdk extends ExecutableTaskGraphTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
