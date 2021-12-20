package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.values.ObjectMap;

public class PreviousResultsAcceptorDefault implements PreviousResultsAcceptor {

	@Override
	public boolean acceptPreviousResults(ObjectMap results) {
		return true;
	}


}
