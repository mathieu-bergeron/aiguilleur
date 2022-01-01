package ca.ntro.core.task_graphs.executable_task_graph;

import org.junit.Test;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.tests.NtroTests;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.result.Result;

public class ExecutableTaskGraphTests extends NtroTests {
	
	/*
	
	@Test
	public void executableTaskGraph01() {
		ExecutableTaskGraph graph = ExecutableTaskGraph.newExecutableGraph();
		graph.setGraphName("executableTaskGraph01");
		
		Task taskB = graph.addTask("B");
		Task taskA = taskB.addPreviousTask("A");
		
		AtomicTask a_entry = taskA.addEntryTask("a_entry");
		AtomicTask b_entry = taskB.addEntryTask("b_entry");
		
		a_entry.execute((previousResults, notifyer) -> {
			notifyer.addResult(1);
			notifyer.addResult(2);
		});

		a_entry.handleException(exception -> {
			Ntro.exceptions().throwException(exception);
		});
		
		// MsgReceiver: server-side must not stay inProgress
		b_entry.execute((previousResults, notifyer) -> {
			notifyer.notifyTaskBlocked();

			notifyer.addResult(0);

			Ntro.time().runAfterDelay(10, () -> {
				notifyer.addResult(1);
			});

			Ntro.time().runAfterDelay(20, () -> {
				notifyer.addResult(2);
			});
		});
		
		b_entry.cancel((previousResults, notifyer) -> {
			notifyer.notifyTaskBlocked();
		});

		// XXX: must allow more time if we write graph
		Result<ObjectMap> result = graph.executeBlocking();
		
		Integer a_entry_result = result.value().get(Integer.class, "a_entry");
		Integer b_entry_result = result.value().get(Integer.class, "b_entry");


		Ntro.asserter().assertEquals(2, a_entry_result);
		Ntro.asserter().assertEquals(null, b_entry_result);
	}

	@Test
	public void executableTaskGraph02() {

		ExecutableTaskGraph graph = ExecutableTaskGraph.newExecutableGraph();
		graph.setGraphName("executableTaskGraph02");
		
		Task taskA = graph.addTask("A");
		Task taskB = taskA.addNextTask("B");
		Task taskC = taskA.addNextTask("C");
		Task taskD = taskC.addPreviousTask("D");

		AtomicTask a_entry = taskA.addEntryTask("a_entry");
		AtomicTask b_entry = taskB.addEntryTask("b_entry");
		AtomicTask c_entry = taskC.addEntryTask("c_entry");
		AtomicTask d_entry = taskD.addEntryTask("d_entry");
		
		// MsgReceiver: blocked until it has results
		a_entry.execute((previousResults, notifyer) -> {
			notifyer.notifyTaskBlocked();

			notifyer.addResult(1);
			notifyer.addResult(2);
		});
		
		// MsgReceiver: blocked when no results
		a_entry.cancel((previousResults, notifyer) -> {
			notifyer.notifyTaskBlocked();
		});


		b_entry.execute((previousResults, notifyer) -> {
			Ntro.time().runAfterDelay(10, () -> {
				notifyer.addResult(1);
			});
		});

		c_entry.execute((previousResults, notifyer) -> {
			Integer a_entry_result = previousResults.get(Integer.class, "a_entry");

			notifyer.addResult(a_entry_result + 1);
		});

		d_entry.execute((previousResults, notifyer) -> {
			Ntro.time().runAfterDelay(300, () -> {
				notifyer.addResult(1);
			});
		});

		// XXX: must allow more time if we write graph
		Result<ObjectMap> result = graph.executeBlocking();
		
		Integer a_entry_result = result.value().get(Integer.class, "a_entry");
		Integer c_entry_result = result.value().get(Integer.class, "c_entry");

		Ntro.asserter().assertEquals(2, a_entry_result);
		Ntro.asserter().assertEquals(3, c_entry_result);
	}

	@Test
	public void executableTaskGraphException01() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		ExecutableTaskGraph graph = ExecutableTaskGraph.newExecutableGraph();
		
		Task taskA = graph.addTask("A");
		AtomicTask a_entry = taskA.addEntryTask("a_entry");
		
		a_entry.execute((currentResults, notifyer) -> {

			String[] array = new String[] {"a","b"};
			String outOfBounds = array[2];
		});


		Result<ObjectMap> result = graph.executeBlocking();

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.hasThrown());
		Ntro.asserter().assertTrue("Has thrown OutOfBounds", exceptionThrower.wasThrown(ArrayIndexOutOfBoundsException.class));
	}
	
	*/
}
