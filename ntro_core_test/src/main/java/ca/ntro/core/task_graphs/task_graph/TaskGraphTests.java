package ca.ntro.core.task_graphs.task_graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graph_writer.NodeNotFoundException;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.wrappers.result.ResultNtro;

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
		
		MockTask taskA = graph.addTask("A");

		MockTask taskAA = taskA.addSubTask("AA");
		
		MockTask taskAB = taskAA.addNextTask("AB");
		taskA.addSubTask(taskAB);

		MockTask taskB = taskA.addNextTask("B");

		MockAtomicTask a_entry = taskA.addEntryTask("a_entry");
		MockAtomicTask a_exit = taskA.addExitTask("a_exit");

		MockAtomicTask aa_entry = taskAA.addEntryTask("aa_entry");

		graph.setGraphName("simpleTaskGraph01_00");
		graph.write(Ntro.graphWriter());
		
		a_entry.registerNewResult(new ResultNtro<Integer>(1));

		graph.setGraphName("simpleTaskGraph01_01");
		graph.write(Ntro.graphWriter());

		aa_entry.registerNewResult(new ResultNtro<Integer>(1));

		graph.setGraphName("simpleTaskGraph01_02");
		graph.write(Ntro.graphWriter());

		ResultNtro<Integer> a_result = new ResultNtro<Integer>(1);
		a_exit.registerNewResult(a_result);
		
		graph.setGraphName("simpleTaskGraph01_03");
		graph.write(Ntro.graphWriter());

		a_result.registerException(new NodeNotFoundException("test"));
		
		graph.setGraphName("simpleTaskGraph01_04");
		graph.write(Ntro.graphWriter());
		
		
	}
}
