package ca.ntro.core.task_graphs.executable_task_graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.optionnal.OptionnalNtro;
import ca.ntro.core.wrappers.result.Result;

public class ExecutableTaskGraphTests {
	
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
	public void executableTaskGraph01() {

		ExecutableTaskGraph graph = ExecutableTaskGraph.newExecutableGraph();
		graph.setGraphName("executableTaskGraph01");
		
		ExecutableTask taskA = graph.addTask("A");
		ExecutableTask taskB = taskA.addNextTask("B");
		
		ExecutableAtomicTask a_entry = taskA.addEntryTask("a_entry");
		ExecutableAtomicTask b_entry = taskB.addEntryTask("b_entry");
		
		OptionnalNtro<Boolean> a_entry_ran = new OptionnalNtro<Boolean>();
		
		a_entry.execute((previousResults, notifyer) -> {
			Ntro.time().runAfterDelay(5, () -> {
				notifyer.addResult(1);
				a_entry_ran.registerValue(true);
			});
		});

		a_entry.cancel((previousResults, notifyer) -> {
			notifyer.notifyTaskBlocked();
		});

		a_entry.handleException(exception -> {
			Ntro.exceptions().throwException(exception);
		});
		
		// MsgReceiver: task is blocked until we have results
		b_entry.execute((previousResults, notifyer) -> {
			notifyer.notifyTaskBlocked();

			Ntro.time().runAfterDelay(5, () -> {
				notifyer.addResult(1);
			});
		});
		
		b_entry.cancel((previousResults, notifyer) -> {
			notifyer.notifyTaskBlocked();
		});

		// XXX: must allow more time if we write graph
		Result<ObjectMap> result = graph.executeBlocking(1000, Ntro.graphWriter());
		
		Ntro.asserter().assertTrue("a_entry ran", a_entry_ran.value());

		Integer a_entry_result = result.value().get(Integer.class, "a_entry");
		Integer b_entry_result = result.value().get(Integer.class, "b_entry");

		Ntro.asserter().assertEquals(1, a_entry_result);
		Ntro.asserter().assertEquals(1, b_entry_result);

	}

	@Test
	public void executableTaskGraph02() {

		ExecutableTaskGraph graph = ExecutableTaskGraph.newExecutableGraph();
		graph.setGraphName("executableTaskGraph02");
		
		ExecutableTask taskA = graph.addTask("A");
		ExecutableTask taskB = taskA.addNextTask("B");
		ExecutableTaskNtro taskC = taskA.addNextTask("C");

		ExecutableTask taskD = graph.addTask("D");
		taskD.addNextTask(taskC);
		
		ExecutableAtomicTask a_entry = taskA.addEntryTask("a_entry");
		ExecutableAtomicTask b_entry = taskB.addEntryTask("b_entry");
		ExecutableAtomicTask c_entry = taskC.addEntryTask("c_entry");
		ExecutableAtomicTask d_entry = taskD.addEntryTask("d_entry");
		
		// MsgReceiver: blocked until it has results
		a_entry.execute((previousResults, notifyer) -> {
			notifyer.notifyTaskBlocked();

			Ntro.time().runAfterDelay(5, () -> {
				notifyer.addResult(1);
				notifyer.addResult(2);
				notifyer.addResult(3);
				notifyer.addResult(4);
			});
		});

		
		// XXX: must allow more time if we write graph
		Result<ObjectMap> result = graph.executeBlocking(1000, Ntro.graphWriter());
		
		Integer a_entry_result = result.value().get(Integer.class, "a_entry");

		Ntro.asserter().assertEquals(1, a_entry_result);
	}

	@Test
	public void executableTaskGraphException01() {
		ExceptionThrowerMock exceptionThrower = registerMockExceptionThrower();

		ExecutableTaskGraph graph = ExecutableTaskGraph.newExecutableGraph();
		
		ExecutableTask taskA = graph.addTask("A");
		ExecutableAtomicTask a_entry = taskA.addEntryTask("a_entry");
		
		a_entry.execute((currentResults, notifyer) -> {

			String[] array = new String[] {"a","b"};
			String outOfBounds = array[2];
		});


		Result<ObjectMap> result = graph.executeBlocking(10);

		Ntro.asserter().assertTrue("Should throw", exceptionThrower.hasThrown());
		Ntro.asserter().assertTrue("Has thrown OutOfBounds", exceptionThrower.wasThrown(ArrayIndexOutOfBoundsException.class));
	}
}
