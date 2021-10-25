package ca.ntro.jj.init;

import ca.jj.core.task_graph.TaskGraph;
import ca.ntro.jj.wrappers.future.Future;

public class InitializatorJj implements Initializator {
	
	private InitializationOptions options = new InitializationOptionsJj();

	private TaskGraph buildGraph() {
		return null;
	}

	@Override
	public Future<Void> execute() {
		return buildGraph().execute();
	}

	@Override
	public Future<Void> executeBlocking() {
		return buildGraph().executeBlocking();
	}

	@Override
	public Initializator setOptions(InitializationOptions options) {
		this.options = options;

		return this;
	}
}
