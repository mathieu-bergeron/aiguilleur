package ca.ntro.core.task_graphs.task_graph;

public interface AtomicTaskOptions {

	boolean keepLastResult();

	public static AtomicTaskOptions consumeResult(boolean consumeResult) {
		AtomicTaskOptionsNtro options = new AtomicTaskOptionsNtro();
		
		options.setConsumeResult(consumeResult);

		return options;
	}

}
