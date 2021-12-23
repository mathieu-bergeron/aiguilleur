package ca.ntro.core.task_graphs.task_graph;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graph_writer.NodeNotFoundException;
import ca.ntro.core.initialization.InitializerTest;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.services.ExceptionThrowerMock;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
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
	public void simpleTaskGraph00() {

		/* In fact, the state of the TaskGraph
		 * 
		 * depends entirely on the state of the
		 * resultsIterator
		 * 
		 * when calling graph.resultsIterator()
		 * 
		 * it creates a structure that mimmick the
		 * structure of the taskGraph but
		 * also contains results
		 * 
		 * so the state of the TaskGraph changes when
		 * 
		 * iterator.advanceToNextResult(); is called
		 * 
		 * (some tasks could have empty results)
		 * 
		 * 
		 * 
		 */
		throw new RuntimeException("TODO");

	}
	
	@Test
	public void simpleTaskGraph01() {
		
		
		TaskGraph<MockTask, MockAtomicTask> graph = TaskGraph.newGraph(MockTask.class, MockAtomicTask.class);
		
		MockTask taskA = graph.addTask("A");

		MockTask taskAA = taskA.addSubTask("AA");
		
		MockTask taskAB = taskAA.addNextTask("AB");
		taskA.addSubTask(taskAB);

		taskA.addNextTask("B");

		MockAtomicTask a_entry = taskA.addEntryTask("a_entry");
		MockAtomicTask a_exit = taskA.addExitTask("a_exit");

		MockAtomicTask aa_entry = taskAA.addEntryTask("aa_entry");

		graph.setGraphName("simpleTaskGraph01");
		graph.write(Ntro.graphWriter());

		TaskGraphTrace trace = graph.newTrace();
		
		a_entry.addResult(1);
		aa_entry.addResult(1);
		aa_entry.addResult(2);
		aa_entry.addResult(3);
		aa_entry.addResult(4);
		a_exit.addResult(1);

		trace.write(Ntro.graphWriter());

		Ntro.asserter().assertTrue("has results", trace.hasCurrent());

		ObjectMap results = trace.current();
		
		trace.advanceToNext();
		trace.write(Ntro.graphWriter());

		trace.advanceToNext();
		trace.write(Ntro.graphWriter());

		ObjectMap nextResults = trace.current();

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
