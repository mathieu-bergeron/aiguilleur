package ca.ntro.core.task_graphs.executable_task_graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
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
	public void executeBlocking01() {

		ExecutableTaskGraph graph = ExecutableTaskGraph.newExecutableGraph();
		
		ExecutableTask taskA = graph.addTask("A");
		
		ExecutableAtomicTask a_entry = taskA.addEntryTask("a_entry");

		graph.setGraphName("executeBlocking01_00");
		graph.write(Ntro.graphWriter());
		
		a_entry.onStart((currentResults, notifyer) -> {
			
			Ntro.time().sleep(1);

			notifyer.registerResult(1);
		});

		a_entry.onSuspend(currentResults -> {
			
		});

		Result<ObjectMap> result = graph.executeBlocking();

		graph.setGraphName("executeBlocking01_01");
		graph.write(Ntro.graphWriter());
		
		Integer a_entry_result = null;

		Ntro.asserter().assertTrue("should have value", result.hasValue());
		
		if(result.hasValue()) {
			a_entry_result = result.value().getObject(Integer.class, "a_entry");
		}

		Ntro.asserter().assertEquals(1, a_entry_result);

	}

	@Test
	public void executeAsync01() {

		ExecutableTaskGraph graph = ExecutableTaskGraph.newExecutableGraph();
		
		ExecutableTask taskA = graph.addTask("A");
		
		ExecutableAtomicTask a_entry = taskA.addEntryTask("a_entry");

		graph.setGraphName("executeAsync01_00");
		graph.write(Ntro.graphWriter());
		
		a_entry.onStart((currentResults, notifyer) -> {
			
			Ntro.time().sleep(1);

			notifyer.registerResult(1);
		});

		a_entry.onSuspend(currentResults -> {
			
		});

		Future<ObjectMap> future = graph.execute();
		
		future.handleValue(objectMap -> {

			graph.setGraphName("executeAsync01_01");
			graph.write(Ntro.graphWriter());
			
			Integer a_entry_result = null;

			a_entry_result = objectMap.getObject(Integer.class, "a_entry");

			Ntro.asserter().assertEquals(1, a_entry_result);
		});
	}
}
