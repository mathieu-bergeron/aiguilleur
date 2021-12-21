package ca.ntro.core.task_graphs.executable_task_graph;


import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.future.FutureNtro;
import ca.ntro.core.wrappers.result.Result;

public class ExecutableTaskGraphNtro

       extends TaskGraphNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTaskGraph {
	
	private boolean executionInProgress = false;
	private boolean writeGraph = false;
	
	private FutureNtro<ObjectMap> future;
	
	private String baseGraphName;
	private long executionStep;

	@Override
	public Future<ObjectMap> execute() {
		return execute(false);
	}

	@Override
	public Future<ObjectMap> execute(boolean writeGraph) {
		if(executionInProgress()) {
			Ntro.exceptions().throwException(new IllegalStateException("We are already executing"));
		}

	    setExecutionInProgress(true);
	    
	    this.writeGraph = writeGraph;

		this.future = new FutureNtro<>();
		this.executionStep = 0;

		GraphId id = getHdagBuilder().getGraph().id();
		if(id == null) {
			id = GraphId.newGraphId();
		}
		this.baseGraphName = id.toKey().toString();
		
		startExecution();

		return future;
	}
	
	private void writeGraph() {
		if(writeGraph) {

			getHdagBuilder().setGraphName(baseGraphName + "_" +  executionStep);
			
			write(Ntro.graphWriter());
			
			executionStep++;
		}
	}
	
	private synchronized boolean executionInProgress() {
		return executionInProgress;
	}

	private synchronized void setExecutionInProgress(boolean executionInProgress) {
		this.executionInProgress = executionInProgress;
	}
	
	private synchronized void halt() {
		setExecutionInProgress(false);

		if(!future.hasException()) {
			future.registerValue(results());
		}
	}

	public void notifyOfException(Throwable t) {
		future.registerException(t);
		halt();
	}
	
	public void notifyOfNewResult() {
		try {

			continueExecution();

		}catch(Throwable t) {

			future.registerException(t);
			halt();
		}
	}
	
	private ExecutableTaskNtro toTaskNtro(ExecutableTask task) {
		return (ExecutableTaskNtro) task;
	}

	private void startExecution() {
		if(!executionInProgress()) return;

		writeGraph();
			
		ObjectMap currentResults = results();
		
		tasks().forEach(task -> {
			
			toTaskNtro(task).continueExecution(currentResults);
			
		});
		
		writeGraph();
		
		if(!isInProgress()) {
			halt();
		}
	}


	private boolean isInProgress() {
		return tasks().ifSome(task -> task.isInProgress());
	}

	private void continueExecution() {
		if(!executionInProgress()) return;
		
		if(hasNextResults()) {

			ObjectMap currentResults = nextResults();

			writeGraph();

			tasks().forEach(task -> {
				
				toTaskNtro(task).continueExecution(currentResults);
				
			});

			writeGraph();
		}

		if(!isInProgress()) {
			halt();
		}
	}

	@Override
	public Result<ObjectMap> executeBlocking() {
		return execute().get();
	}

	@Override
	public Result<ObjectMap> executeBlocking(long maxDelayMillis) {
		return executeBlocking(maxDelayMillis, false);
	}

	@Override
	public Result<ObjectMap> executeBlocking(long maxDelayMillis, boolean writeGraph) {
		return execute(writeGraph).get(maxDelayMillis);
	}


}
