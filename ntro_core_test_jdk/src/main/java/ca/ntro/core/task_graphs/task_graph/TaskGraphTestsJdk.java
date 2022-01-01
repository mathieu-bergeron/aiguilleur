package ca.ntro.core.task_graphs.task_graph;

import org.junit.BeforeClass;

import ca.ntro.core.initialization.InitializerTestJdk;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphTests;

public class TaskGraphTestsJdk extends GenericTaskGraphTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

}
