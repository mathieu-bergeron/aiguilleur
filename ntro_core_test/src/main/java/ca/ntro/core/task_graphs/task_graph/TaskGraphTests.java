package ca.ntro.core.task_graphs.task_graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;

public class TaskGraphTests {
	
	protected ExceptionThrowerMock registerMockExceptionThrower() {
		ExceptionThrowerMock exceptionThrowerMock = new ExceptionThrowerMock();

		InitializerTest.registerExceptionThrower(exceptionThrowerMock);

		return exceptionThrowerMock;
	}

	@BeforeClass
	public static void initialize() {
		InitializerTest.initialize();
	}
	
	@Test
	public void simpleTaskGraph01() {
		
		TaskGraph<MockTask, MockAtomicTask> graph = TaskGraph.newGraph(MockTask.class, MockAtomicTask.class);
		graph.setGraphName("simpleTaskGraph01");
		
		MockTask taskA = graph.addTask("A");
		MockTask taskAA = graph.addTask("AA");

		MockTask taskB = graph.addTask("B");
		
		taskA.addSubTask(taskAA);
		taskA.addNextTask(taskB);

		graph.write(Ntro.graphWriter());
	}
}
