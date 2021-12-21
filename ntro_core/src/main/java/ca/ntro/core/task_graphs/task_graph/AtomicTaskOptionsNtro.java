package ca.ntro.core.task_graphs.task_graph;

public class AtomicTaskOptionsNtro implements AtomicTaskOptions {
	
	private boolean consumeResult = false;

	public boolean getConsumeResult() {
		return consumeResult;
	}

	public void setConsumeResult(boolean consumeResult) {
		this.consumeResult = consumeResult;
	}


	@Override
	public boolean consumeFirstResult() {
		return getConsumeResult();
	}

}
