package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.ResultIterator;

public class OnOffAtomicTask extends ExecutableAtomicTaskNtro {

	@Override
	public ResultIterator resultIterator() {
		// XXX: different type of atomic tasks
		//      have different type of resultsIterator
		throw new RuntimeException("TODO");
	}

}
