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

	    // JSweet error: "supplied parameters do not match any signature of call target"
		// MockTask taskA = graph.createTask("A");
		
		MockTask taskA = graph.createTask(new TaskIdNtro("A"));
		MockTask taskAA = graph.createTask(new TaskIdNtro("AA"));

		MockTask taskB = graph.createTask(new TaskIdNtro("B"));
		
		taskA.addSubTask(taskAA);
		taskA.addNextTask(taskB);

		graph.addTask(taskA);
		graph.addTask(taskB);
		graph.addTask(taskAA);

		graph.write(Ntro.graphWriter());
	}
}
