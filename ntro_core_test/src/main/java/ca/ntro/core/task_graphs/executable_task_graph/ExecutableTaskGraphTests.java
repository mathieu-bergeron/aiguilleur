package ca.ntro.core.task_graphs.executable_task_graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.values.ObjectMap;
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
		
		a_entry.onStart((currentResults, notifyer) -> {

			Ntro.time().runAfterDelay(5, () -> {
				notifyer.registerResult(1);
			});
		});

		a_entry.onSuspend(currentResults -> {
			
		});
		
		b_entry.onStart((currentResults, notifyer) -> {

			Ntro.time().runAfterDelay(5, () -> {
				notifyer.registerResult(1);
			});
		});

		Result<ObjectMap> result = graph.executeBlocking(1000, Ntro.graphWriter());
		//Result<ObjectMap> result = graph.executeBlocking(1000);
		
		Integer a_entry_result = result.value().getObject(Integer.class, "a_entry");
		Integer b_entry_result = result.value().getObject(Integer.class, "b_entry");

		Ntro.asserter().assertEquals(1, a_entry_result);
		Ntro.asserter().assertEquals(1, b_entry_result);

	}

	@Test
	public void executableTaskGraphException01() {

		ExecutableTaskGraph graph = ExecutableTaskGraph.newExecutableGraph();
		
		ExecutableTask taskA = graph.addTask("A");
		ExecutableAtomicTask a_entry = taskA.addEntryTask("a_entry");
		
		a_entry.onStart((currentResults, notifyer) -> {

			String[] array = new String[] {"a","b"};
			String outOfBounds = array[2];
		});

		Result<ObjectMap> result = graph.executeBlocking(10);
		
		Ntro.asserter().assertTrue("should have exception", result.hasException());

		Ntro.asserter().assertTrue("should have OutOfBounds", result.exception() instanceof IndexOutOfBoundsException);

	}
}
