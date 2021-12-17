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
		
		ExecutableTask taskA = graph.addTask("A");
		
		ExecutableAtomicTask a_entry = taskA.addEntryTask("a_entry");

		graph.setGraphName("executableTaskGraph01_00");
		graph.write(Ntro.graphWriter());
		
		a_entry.onStart((currentResults, notifyer) -> {
			
			// FIXME: this consumes the exceptions
			Ntro.time().runAfterDelay(5, () -> {

				notifyer.registerResult(1);

			});
		});

		a_entry.onSuspend(currentResults -> {
			
		});

		Result<ObjectMap> result = graph.executeBlocking(10);

		graph.setGraphName("executableTaskGraph01_01");
		graph.write(Ntro.graphWriter());
		
		Integer a_entry_result = result.value().getObject(Integer.class, "a_entry");

		Ntro.asserter().assertEquals(1, a_entry_result);

	}
}
