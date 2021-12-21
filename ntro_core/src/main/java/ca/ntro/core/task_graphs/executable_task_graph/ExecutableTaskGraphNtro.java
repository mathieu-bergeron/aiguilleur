package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.concurrent.TimeoutException;

import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.future.FutureNtro;
import ca.ntro.core.wrappers.optionnal.Optionnal;
import ca.ntro.core.wrappers.result.Result;

public class ExecutableTaskGraphNtro

       extends TaskGraphNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTaskGraph {
	
	public static final long DEFAULT_MAX_DELAY_MILLIS = 30 * 1000;

	private boolean executionInProgress = false;
	private boolean writeGraph = false;
	
	private FutureNtro<ObjectMap> future;
	
	private String baseGraphName;
	private long executionStep;

	@Override
	public Future<ObjectMap> execute() {
		return execute(Optionnal.none(Long.class), false);
	}

	@Override
	public Future<ObjectMap> execute(long maxDelayMillis) {
		return execute(maxDelayMillis, false);
	}

	@Override
	public Future<ObjectMap> execute(long maxDelayMillis, boolean writeGraph) {
		return execute(Optionnal.fromValue(maxDelayMillis), writeGraph);
	}

	public Future<ObjectMap> execute(Optionnal<Long> maxDelayMillis, boolean writeGraph) {
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
		
		if(maxDelayMillis.hasValue()) {
			Ntro.time().runAfterDelay(maxDelayMillis.value(), () -> {
				future.registerException(new TimeoutException());
				halt();
			});
		}

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
		return execute(Optionnal.none(Long.class), writeGraph).get();
	}

	@Override
	public Result<ObjectMap> executeBlocking(long maxDelayMillis) {
		return executeBlocking(maxDelayMillis, false);
	}

	@Override
	public Result<ObjectMap> executeBlocking(long maxDelayMillis, boolean writeGraph) {
		return execute(Optionnal.none(Long.class), writeGraph).get(maxDelayMillis);
	}


}
