package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graph_writer.GraphWriterNull;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.identifyers.Id;
import ca.ntro.core.identifyers.IdNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskId;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.future.FutureNtro;
import ca.ntro.core.wrappers.result.Result;

public class ExecutableTaskGraphNtro

       extends TaskGraphNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTaskGraph, ObjectMap {
	
	public static final long DEFAULT_MAX_DELAY_MILLIS = 30 * 1000;

	private Map<String, ExecutableTaskNtro> blocked;
	private Map<String, ExecutableTaskNtro> inProgress;
	private Map<String, ExecutableTaskNtro> done;
	
	private boolean executionInProgress = false;
	
	private FutureNtro<ObjectMap> future;
	
	private GraphWriter writer;
	private String baseGraphName;
	private long executionStep;

	@Override
	public Future<ObjectMap> execute(long maxDelayMillis) {
		return execute(maxDelayMillis, new GraphWriterNull());
	}

	@Override
	public Future<ObjectMap> execute(long maxDelayMillis, GraphWriter writer) {
		if(executionInProgress()) {
			Ntro.exceptions().throwException(new IllegalStateException("We are already executing"));
		}

		blocked = new HashMap<>();
		inProgress = new HashMap<>();
	    done = new HashMap<>();

	    setExecutionInProgress(true);

		this.future = new FutureNtro<>();
		this.writer = writer;
		this.executionStep = 0;
		GraphId id = getHdagBuilder().getGraph().id();
		if(id == null) {
			id = GraphId.newGraphId();
		}
		this.baseGraphName = id.toKey().toString();
		
		startExecution();
		
		Ntro.time().runAfterDelay(maxDelayMillis, () -> {
			future.registerException(new TimeoutException());
			halt();
		});

		return future;
	}
	
	private void writeGraph() {
		getHdagBuilder().setGraphName(baseGraphName + "_" +  executionStep);
		
		write(writer);
		
		executionStep++;
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
			future.registerValue((ObjectMap) this);
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
	
	private void startExecution() {
		if(!executionInProgress()) return;

		writeGraph();
		
		tasks().forEach(task -> {
			if(future.hasException()) {
				throw new Break();
			}

			if(task.isBlocked()) {
				
				blocked.put(task.id().toKey().toString(), task);
				
			}else if(task.isInProgress()) {
				
				inProgress.put(task.id().toKey().toString(), task);
				task.execute();
				
			}else if(task.isDone()) {
				
				done.put(task.id().toKey().toString(), task);
			}
		});

		writeGraph();

		if(inProgress.isEmpty()) {
			halt();
		}
	}

	private void continueExecution() {
		if(!executionInProgress()) return;

		writeGraph();
		
		Map<String, ExecutableTaskNtro> newBlocked = new HashMap<>();
		Map<String, ExecutableTaskNtro> newInProgress = new HashMap<>();
		Map<String, ExecutableTaskNtro> newDone = new HashMap<>();
		
		processBlockedTasks(newBlocked, newInProgress, newDone);
		processInProgressTasks(newBlocked, newInProgress, newDone);
		processDoneTasks(newBlocked, newInProgress, newDone);
		
		blocked = newBlocked;
		inProgress = newInProgress;
		done = newDone;


		if(inProgress.isEmpty()) {

			halt();

		}else {
			
			writeGraph();
			
		}
	}

	private void processBlockedTasks(Map<String, ExecutableTaskNtro> newBlocked, 
			                         Map<String, ExecutableTaskNtro> newInProgress, 
			                         Map<String, ExecutableTaskNtro> newDone) {
		
		for(Map.Entry<String, ExecutableTaskNtro> entry : blocked.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isWaiting()) {
				
				newBlocked.put(taskId, task);
				
			}else if(task.isInProgress()) {
				
				newInProgress.put(taskId, task);
				task.execute();
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
				task.cancel();
			}
		}
	}

	private void processInProgressTasks(Map<String, ExecutableTaskNtro> newBlocked, 
			                            Map<String, ExecutableTaskNtro> newInProgress, 
			                            Map<String, ExecutableTaskNtro> newDone) {
		
		for(Map.Entry<String, ExecutableTaskNtro> entry : inProgress.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isWaiting()) {
				
				newBlocked.put(taskId, task);
				task.suspend();
				
			}else if(task.isInProgress()) {
				
				newInProgress.put(taskId, task);
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
				task.cancel();
			}
		}
	}

	private void processDoneTasks(Map<String, ExecutableTaskNtro> newBlocked, 
			                      Map<String, ExecutableTaskNtro> newInProgress, 
			                      Map<String, ExecutableTaskNtro> newDone) {

		for(Map.Entry<String, ExecutableTaskNtro> entry : done.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isWaiting()) {
				
				newBlocked.put(taskId, task);
				
			}else if(task.isInProgress()) {
				
				newInProgress.put(taskId, task);
				task.execute();
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
			}
		}
	}
	
	@Override
	public Result<ObjectMap> executeBlocking() {
		return executeBlocking(DEFAULT_MAX_DELAY_MILLIS);
	}

	@Override
	public Result<ObjectMap> executeBlocking(long maxDelayMillis) {
		return executeBlocking(maxDelayMillis, new GraphWriterNull());
	}

	@Override
	public Result<ObjectMap> executeBlocking(long maxDelayMillis, GraphWriter writer) {
		return execute(maxDelayMillis, writer).get(maxDelayMillis);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <O> O get(Class<O> _class, Id id) {
		O result = null;
		
		ExecutableAtomicTaskNtro atomicTask = findAtomicTask(AtomicTaskId.fromKey(id.toKey()));
		
		if(atomicTask.result().hasValue()) {
			result = (O) atomicTask.result().value();
		}

		return result;
	}

	@Override
	public <O> O get(Class<O> _class, String id) {
		return get(_class, new IdNtro(id));
	}


}
