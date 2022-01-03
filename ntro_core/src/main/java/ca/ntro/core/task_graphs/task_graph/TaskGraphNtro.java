package ca.ntro.core.task_graphs.task_graph;


import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTraceNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.future.FutureNtro;
import ca.ntro.core.wrappers.result.Result;

public class TaskGraphNtro

       extends GenericTaskGraphNtro<Task, AtomicTask>

       implements TaskGraph {
	
	private boolean executionInProgress = false;
	
	private TaskGraphOptions options = new TaskGraphOptionsDefault();

	private TaskGraphTraceNtro trace;
	
	private FutureNtro<ObjectMap> future;
	private ObjectMap currentResults;

	@Override
	public Future<ObjectMap> execute(TaskGraphOptions options) {
		this.options = options;
		return execute();
	}

	@Override
	public Future<ObjectMap> execute() {
		if(executionInProgress()) {
			Ntro.exceptions().throwException(new IllegalStateException("We are already executing"));
		}

	    setExecutionInProgress(true);
	    
		this.future = new FutureNtro<>();
		
		trace = (TaskGraphTraceNtro) newTrace();
		
		trace.onExecutionStep((stateChanged) -> {
			handleExecutionStep(stateChanged);
		});

		handleExecutionStep(true);

		return future;
	}

	private void handleExecutionStep(boolean stateChanged) {
		if(options.shouldWriteGraph()
				&& stateChanged) {

			trace.writeCurrentState(Ntro.graphWriter());
		}
		
		if(options.shouldHalt(trace.getGraph(), trace)) {
			
			halt();

		}else if(stateChanged){

			trace.executeOneStep();

		}else if(trace.hasNext()){

			trace.advanceToNext();
			trace.executeOneStep();
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
			future.registerValue(currentResults);
		}
	}

	@Override
	public Result<ObjectMap> executeBlocking() {
		return execute().get();
	}

	@Override
	public Result<ObjectMap> executeBlocking(TaskGraphOptions options) {
		return execute(options).get(options.maxDelayMillis());
	}


}
