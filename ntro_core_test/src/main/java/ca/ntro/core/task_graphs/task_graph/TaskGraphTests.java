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
		MockTaskGraph graph = new MockTaskGraph("simpleTaskGraph01");

		MockTask taskA = graph.createTask("A");
		MockTask taskAA = graph.createTask("AA");

		MockTask taskB = graph.createTask("B");
		
		taskA.addSubTask(taskAA);
		taskA.addNextTask(taskB);

		graph.addTask(taskA);
		graph.addTask(taskB);
		graph.addTask(taskAA);

		graph.write(Ntro.graphWriter());
	}
}
