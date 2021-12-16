package ca.ntro.core.task_graphs.task_graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.identifyers.Key;
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
		MockTask taskAA = graph.addTask("AA");
		MockTask taskAB = graph.addTask("AB");

		MockTask taskB = graph.addTask("B");

		MockTask task0 = graph.addTask("0");
		
		taskA.addSubTask(taskAA);
		taskA.addSubTask(taskAB);
		taskA.addNextTask(taskB);
		
		taskA.addPreviousTask(task0);
		
		taskAA.addNextTask(taskAB);
		
		taskA.addEntryTask(new MockAtomicTask("a_entry"));

		taskAA.addEntryTask(new MockAtomicTask("aa_entry"));

		taskA.addExitTask(new MockAtomicTask("a_exit"));

		MockAtomicTask a_entry = taskA.findEntryTask(AtomicTaskId.fromKey(new Key("a_entry")));
		MockAtomicTask aa_entry = taskAA.findEntryTask(AtomicTaskId.fromKey(new Key("aa_entry")));
		MockAtomicTask a_exit = taskA.findExitTask(AtomicTaskId.fromKey(new Key("a_exit")));

		graph.setGraphName("simpleTaskGraph01_00");
		graph.write(Ntro.graphWriter());
		
		a_entry.registerResult(new ResultNtro<Integer>(1));

		graph.setGraphName("simpleTaskGraph01_01");
		graph.write(Ntro.graphWriter());

		aa_entry.registerResult(new ResultNtro<Integer>(1));

		graph.setGraphName("simpleTaskGraph01_02");
		graph.write(Ntro.graphWriter());

		a_exit.registerResult(new ResultNtro<Integer>(1));

		graph.setGraphName("simpleTaskGraph01_03");
		graph.write(Ntro.graphWriter());

		
		
	}
}
