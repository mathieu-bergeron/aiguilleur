package ca.ntro.core.task_graphs.task_graph;


import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskGraphNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.future.FutureNtro;
import ca.ntro.core.wrappers.result.Result;

public class TaskGraphNtro

       extends GenericTaskGraphNtro<Task, AtomicTask>

       implements TaskGraph {
	
	private boolean executionInProgress = false;
	
	private TaskGraphOptions options = new TaskGraphOptionsDefault();

	private TaskGraphTrace trace;
	
	private FutureNtro<ObjectMap> future;
	private ObjectMap currentResults;
	
	private String baseGraphName;
	private long executionStep;

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
		this.executionStep = 0;

		GraphId id = getHdagBuilder().getGraph().id();
		if(id == null) {
			id = GraphId.newGraphId();
		}
		this.baseGraphName = id.toKey().toString();
		
		this.trace = newTrace();
		
		startExecution();

		return future;
	}
	
	private void writeGraph() {
		if(options.shouldWriteGraph()) {

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
			future.registerValue(currentResults);
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
	
	private ExecutableTaskNtro toTaskNtro(Task task) {
		return (ExecutableTaskNtro) task;
	}

	private void startExecution() {
		if(!executionInProgress()) return;

		writeGraph();
			
		currentResults = nextResults();

		tasks().forEach(task -> {
			
			//toTaskNtro(task).continueExecution(currentResults);
			
		});
		
		writeGraph();
		
		if(options.shouldHalt(this, this.trace)) {
			halt();
		}
	}

	private void continueExecution() {
		if(!executionInProgress()) return;
		
		if(hasNextResults()) {

			currentResults = nextResults();

			writeGraph();

			tasks().forEach(task -> {
				
				//toTaskNtro(task).continueExecution(currentResults);
				
			});

			writeGraph();
		}

		if(options.shouldHalt(this, this.trace)) {
			halt();
		}
	}

	private ObjectMap nextResults() {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean hasNextResults() {
		// TODO Auto-generated method stub
		return false;
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
