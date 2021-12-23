package ca.ntro.core.task_graphs.task_graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graph_writer.NodeNotFoundException;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.values.ObjectMap;
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
		
		a_entry.addResult(1);

		graph.setGraphName("simpleTaskGraph01_01");
		graph.write(Ntro.graphWriter());

		aa_entry.addResult(1);
		aa_entry.addResult(2);

		graph.setGraphName("simpleTaskGraph01_02");
		graph.write(Ntro.graphWriter());

		ResultNtro<Integer> a_result = new ResultNtro<Integer>(1);
		a_exit.addResult(a_result);
		
		graph.setGraphName("simpleTaskGraph01_03");
		graph.write(Ntro.graphWriter());

		a_result.registerException(new NodeNotFoundException("test"));
		
		graph.setGraphName("simpleTaskGraph01_04");
		graph.write(Ntro.graphWriter());
		
		ResultsIterator iterator = graph.resultsIterator();
		
		Ntro.asserter().assertTrue("has results", iterator.hasResults());

		ObjectMap results = iterator.results();
		
		iterator.advanceToNextResults();
		ObjectMap nextResults = iterator.results();

		graph.setGraphName("simpleTaskGraph01_05");
		graph.write(Ntro.graphWriter());
		
		int a_entry_result = results.get(Integer.class, "a_entry");
		int a_entry_next_result = nextResults.get(Integer.class, "a_entry");

		int aa_entry_result = results.get(Integer.class, "aa_entry");
		int aa_entry_next_result = nextResults.get(Integer.class, "aa_entry");

		Ntro.asserter().assertEquals(1, a_entry_result);
		Ntro.asserter().assertEquals(1, a_entry_next_result);

		Ntro.asserter().assertEquals(1, aa_entry_result);
		Ntro.asserter().assertEquals(2, aa_entry_next_result);

	}
}
