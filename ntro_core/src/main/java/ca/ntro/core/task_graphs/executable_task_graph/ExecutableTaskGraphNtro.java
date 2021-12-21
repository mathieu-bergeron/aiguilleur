package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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
	
	public static final long DEFAULT_MAX_DELAY_MILLIS = 30 * 1000;

	private boolean executionInProgress = false;
	private boolean writeGraph = false;
	
	private FutureNtro<ObjectMap> future;
	
	private String baseGraphName;
	private long executionStep;

	@Override
	public Future<ObjectMap> execute(long maxDelayMillis) {
		return execute(maxDelayMillis, false);
	}

	@Override
	public Future<ObjectMap> execute(long maxDelayMillis, boolean writeGraph) {
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
		
		prepareExecution();
		
		Ntro.time().runAfterDelay(maxDelayMillis, () -> {
			future.registerException(new TimeoutException());
			halt();
		});

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

			throw new RuntimeException("FIXME");
		}
	}

	@Override
	public Future<ObjectMap> execute() {
		return execute(DEFAULT_MAX_DELAY_MILLIS);
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
	
	private void prepareExecution() {
		tasks().forEach(task -> {
			
			toTaskNtro(task).prepareExecution();

		});
	}
	
	private ExecutableTaskNtro toTaskNtro(ExecutableTask task) {
		return (ExecutableTaskNtro) task;
	}
	

	private void continueExecution() {
		if(!executionInProgress()) return;

		writeGraph();
		
		if(hasResults()) {
			
			ObjectMap currentResults = results();
			
			tasks().forEach(task -> {
				
				toTaskNtro(task).continueExecution(currentResults);
				
			});
			
			writeGraph();
			
			nextResults();
			
		}else {

			halt();
		}
	}

	@Override
	public Result<ObjectMap> executeBlocking() {
		return executeBlocking(DEFAULT_MAX_DELAY_MILLIS);
	}

	@Override
	public Result<ObjectMap> executeBlocking(long maxDelayMillis) {
		return executeBlocking(maxDelayMillis, false);
	}

	@Override
	public Result<ObjectMap> executeBlocking(long maxDelayMillis, boolean writeGraph) {
		return execute(maxDelayMillis, writeGraph).get(maxDelayMillis);
	}


}
