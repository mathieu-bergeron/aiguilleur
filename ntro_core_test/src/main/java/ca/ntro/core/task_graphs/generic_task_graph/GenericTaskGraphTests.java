package ca.ntro.core.task_graphs.generic_task_graph;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graph_writer.NodeNotFoundException;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraph;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.result.ResultNtro;

public class GenericTaskGraphTests {
	
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
	public void genericTaskGraph01() {
		
		GenericTaskGraph<MockTask, MockAtomicTask> graph = GenericTaskGraph.newGenericGraph(MockTask.class, MockAtomicTask.class);
		
		MockTask taskA = graph.addTask("A");

		MockTask taskAA = taskA.addSubTask("AA");
		
		MockTask taskAB = taskAA.addNextTask("AB");
		taskA.addSubTask(taskAB);

		MockTask taskB = taskA.addNextTask("B");

		MockAtomicTask a_entry = taskA.addEntryTask("a_entry");
		MockAtomicTask a_exit = taskA.addExitTask("a_exit");

		MockAtomicTask aa_entry = taskAA.addEntryTask("aa_entry");
		
		Ntro.asserter().assertFalse("AA not a previousTask of A", taskA.previousTasks().ifSome(task -> task == taskAA));
		
		graph.setGraphName("genericTaskGraph01");
		graph.write(Ntro.graphWriter());

		TaskGraphTrace trace = graph.newTrace();

		trace.writeCurrentState(Ntro.graphWriter());
		
		a_entry.addResult(1);
		
		trace.writeCurrentState(Ntro.graphWriter());

		aa_entry.addResult(1);

		trace.writeCurrentState(Ntro.graphWriter());
		
		a_exit.addResult(1);
		
		trace.writeCurrentState(Ntro.graphWriter());

		/*
		
		a_entry.addResult(2);
		a_entry.addResult(3);

		

		
		aa_entry.addResult(2);
		aa_entry.addResult(3);

		a_exit.addResult(1);
		a_exit.addResult(2);
		a_exit.addResult(3);

		trace.writeCurrentState(Ntro.graphWriter());
		*/

	}
}
