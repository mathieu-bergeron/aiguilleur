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
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTraceNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.optionnal.Optionnal;
import ca.ntro.core.wrappers.optionnal.OptionnalNtro;
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
		
		graph.setGraphName("genericTaskGraph01");
		graph.write(Ntro.graphWriter());

		OptionnalNtro<Integer> stateChanges = (OptionnalNtro<Integer>) Optionnal.fromValue(0);

		a_entry.execute((previousResults, notifyer) -> {
			// ADS RESULT ONLY TO THE CURRENT TRACE
			notifyer.addResult(2);
		});

		a_entry.handleException(exception -> {
			Ntro.exceptions().throwException(exception);
		});

		TaskGraphTraceNtro trace = (TaskGraphTraceNtro) graph.newTrace();
		trace.writeCurrentState(Ntro.graphWriter());
		
		trace.onStateChange(() -> {
			stateChanges.registerValue(stateChanges.value() + 1);
		});
		
		Ntro.asserter().assertEquals(0, stateChanges.value());
		
		trace.recomputeState();
		trace.writeCurrentState(Ntro.graphWriter());

		Ntro.asserter().assertEquals(1, stateChanges.value());

		// MSG_RECEIVER: ADDING RESULTS TO ALL TRACES
		aa_entry.addResult(1);
		aa_entry.addResult(1);

		trace.writeCurrentState(Ntro.graphWriter());
		
		a_exit.addResult(1);
		a_exit.addResult(1);
		
		trace.writeCurrentState(Ntro.graphWriter());
		
		trace.advanceToNext();
		trace.writeCurrentState(Ntro.graphWriter());

		trace.advanceToNext();
		trace.writeCurrentState(Ntro.graphWriter());
		
		trace.advanceToNext();
		trace.advanceToNext();
		trace.advanceToNext();
		trace.advanceToNext();
		Ntro.asserter().assertTrue("execution can quit on !hasNext()", !trace.hasNext());

	}
}
