package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.exceptions.Break;
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

	private Map<String, ExecutableTaskNtro> blocked = new HashMap<>();
	private Map<String, ExecutableTaskNtro> inProgress = new HashMap<>();
	private Map<String, ExecutableTaskNtro> done = new HashMap<>();
	
	private long maxDelayMillis;
	private long lastChange = 0;
	
	private FutureNtro<ObjectMap> future;

	@Override
	public Future<ObjectMap> execute(long maxDelayMillis) {
		this.maxDelayMillis = maxDelayMillis;
		this.future = new FutureNtro<>();
		
		startExecuting();
		
		return future;
	}

	@Override
	public Future<ObjectMap> execute() {
		return execute(DEFAULT_MAX_DELAY_MILLIS);
	}

	public void notifyOfException(Throwable t) {
		future.registerException(t);
	}
	
	public void notifyOfNewResult() {
		lastChange = Ntro.time().nowMillis();
		
		try {

			resumeExecuting();
			
			if(inProgress.isEmpty()) {

				future.registerValue((ObjectMap) this);
			}

		}catch(Throwable t) {

			future.registerException(t);
		}
	}
	
	private void startExecuting() {
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
		
		lastChange = Ntro.time().nowMillis();
	}
	
	private boolean timeout() {
		return (Ntro.time().nowMillis() - lastChange) > maxDelayMillis;
	}

	private void resumeExecuting() {
		Map<String, ExecutableTaskNtro> newBlocked = new HashMap<>();
		Map<String, ExecutableTaskNtro> newInProgress = new HashMap<>();
		Map<String, ExecutableTaskNtro> newDone = new HashMap<>();
		
		processBlockedTasks(newBlocked, newInProgress, newDone);
		processInProgressTasks(newBlocked, newInProgress, newDone);
		processDoneTasks(newBlocked, newInProgress, newDone);
		
		blocked = newBlocked;
		inProgress = newInProgress;
		done = newDone;
	}

	private void processBlockedTasks(Map<String, ExecutableTaskNtro> newBlocked, 
			                         Map<String, ExecutableTaskNtro> newInProgress, 
			                         Map<String, ExecutableTaskNtro> newDone) {
		
		for(Map.Entry<String, ExecutableTaskNtro> entry : blocked.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isBlocked()) {
				
				newBlocked.put(taskId, task);
				
			}else if(task.isInProgress()) {
				
				newInProgress.put(taskId, task);
				task.execute();
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
				task.stop();
			}
		}
	}

	private void processInProgressTasks(Map<String, ExecutableTaskNtro> newBlocked, 
			                            Map<String, ExecutableTaskNtro> newInProgress, 
			                            Map<String, ExecutableTaskNtro> newDone) {
		
		for(Map.Entry<String, ExecutableTaskNtro> entry : inProgress.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isBlocked()) {
				
				newBlocked.put(taskId, task);
				task.suspend();
				
			}else if(task.isInProgress()) {
				
				newInProgress.put(taskId, task);
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
				task.stop();
			}
		}
	}

	private void processDoneTasks(Map<String, ExecutableTaskNtro> newBlocked, 
			                      Map<String, ExecutableTaskNtro> newInProgress, 
			                      Map<String, ExecutableTaskNtro> newDone) {

		for(Map.Entry<String, ExecutableTaskNtro> entry : done.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isBlocked()) {
				
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
		return execute().get();
	}

	@Override
	public Result<ObjectMap> executeBlocking(long maxDelay) {
		return execute().get(maxDelay);
	}
	
	private boolean taskHasException(ExecutableTask task) {
		return findFirstException(task) != null;
	}

	private Throwable findFirstException(ExecutableTask task) {
		Throwable t = null;

		ExecutableAtomicTask withException = task.entryTasks().findFirst(entryTask -> entryTask.result().hasException());
		
		if(withException == null) {
			
			withException = task.exitTasks().findFirst(exitTask -> exitTask.result().hasException());
		}
		
		if(withException != null) {
			
			t = withException.result().exception();

		}
		
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <O> O getObject(Class<O> _class, Id id) {
		O result = null;
		
		ExecutableAtomicTaskNtro atomicTask = findAtomicTask(AtomicTaskId.fromKey(id.toKey()));
		
		if(atomicTask.result().hasValue()) {
			result = (O) atomicTask.result().value();
		}

		return result;
	}

	@Override
	public <O> O getObject(Class<O> _class, String id) {
		return getObject(_class, new IdNtro(id));
	}
}
