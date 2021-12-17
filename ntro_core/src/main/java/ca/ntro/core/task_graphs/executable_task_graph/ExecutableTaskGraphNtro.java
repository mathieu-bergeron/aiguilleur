package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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
import ca.ntro.core.wrappers.result.ResultNtro;

public class ExecutableTaskGraphNtro

       extends TaskGraphNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTaskGraph, ObjectMap {
	
	public static final long EXECUTABLE_TASK_GRAPH_SLEEP_TIME_MILISECONDS = 10; // FIXME
	public static final long EXECUTABLE_TASK_GRAPH_TIMEOUT_MILISECONDS = 300;   // FIXME

	private Map<String, ExecutableTaskNtro> blocked = new HashMap<>();
	private Map<String, ExecutableTaskNtro> inProgress = new HashMap<>();
	private Map<String, ExecutableTaskNtro> done = new HashMap<>();
	
	private long lastChange = 0;
	
	private FutureNtro<ObjectMap> future;

	@Override
	public Future<ObjectMap> execute() {
		future = new FutureNtro<>();
		
		startExecuting();
		
		return future;
	}
	
	public void notifyOfChange() {
		resumeExecuting();
		
		if(future != null
				&& hasException()) {

			future.registerException(exception());

		}else if(future != null
				&& inProgress.isEmpty()) {

			future.registerValue((ObjectMap) this);
		}
	}
	
	private void startExecuting() {
		tasks().forEach(task -> {
			if(hasException()) {
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
		
		lastChange = Ntro.time().nowMilliseconds();
	}
	
	private boolean timeout() {
		return (Ntro.time().nowMilliseconds() - lastChange) > EXECUTABLE_TASK_GRAPH_TIMEOUT_MILISECONDS;
	}

	private void resumeExecuting() {
		Map<String, ExecutableTaskNtro> newBlocked = new HashMap<>();
		Map<String, ExecutableTaskNtro> newInProgress = new HashMap<>();
		Map<String, ExecutableTaskNtro> newDone = new HashMap<>();
		
		boolean hasChanged = processBlockedTasks(newBlocked, newInProgress, newDone);
		hasChanged = hasChanged || processInProgressTasks(newBlocked, newInProgress, newDone);
		hasChanged = hasChanged || processDoneTasks(newBlocked, newInProgress, newDone);
		
		if(hasChanged) {
			lastChange = Ntro.time().nowMilliseconds();
		}
		
		blocked = newBlocked;
		inProgress = newInProgress;
		done = newDone;
	}

	private boolean processBlockedTasks(Map<String, ExecutableTaskNtro> newBlocked, 
			                            Map<String, ExecutableTaskNtro> newInProgress, 
			                            Map<String, ExecutableTaskNtro> newDone) {
		
		boolean hasChanged = false;

		for(Map.Entry<String, ExecutableTaskNtro> entry : blocked.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isBlocked()) {
				
				newBlocked.put(taskId, task);
				
			}else if(task.isInProgress()) {
				
				newInProgress.put(taskId, task);
				hasChanged = hasChanged || task.execute();
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
				hasChanged = hasChanged || task.stop();
			}
		}
		
		return hasChanged;
	}

	private boolean processInProgressTasks(Map<String, ExecutableTaskNtro> newBlocked, 
			                               Map<String, ExecutableTaskNtro> newInProgress, 
			                               Map<String, ExecutableTaskNtro> newDone) {
		
		boolean hasChanged = false;

		for(Map.Entry<String, ExecutableTaskNtro> entry : inProgress.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isBlocked()) {
				
				newBlocked.put(taskId, task);
				hasChanged = hasChanged || task.suspend();
				
			}else if(task.isInProgress()) {
				
				newInProgress.put(taskId, task);
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
				hasChanged = hasChanged || task.stop();
			}
		}
		
		return hasChanged;
	}

	private boolean processDoneTasks(Map<String, ExecutableTaskNtro> newBlocked, 
			                         Map<String, ExecutableTaskNtro> newInProgress, 
			                         Map<String, ExecutableTaskNtro> newDone) {
		
		boolean hasChanged = false;

		for(Map.Entry<String, ExecutableTaskNtro> entry : done.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isBlocked()) {
				
				newBlocked.put(taskId, task);
				
			}else if(task.isInProgress()) {
				
				newInProgress.put(taskId, task);
				hasChanged = hasChanged || task.execute();
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
			}
		}
		
		return hasChanged;
	}
	
	@Override
	public Result<ObjectMap> executeBlocking() {
		ResultNtro<ObjectMap> result = new ResultNtro<>();
		
		startExecuting();

		while(!inProgress.isEmpty()
				&& !timeout()
				&& !hasException()) {
			
			resumeExecuting();

			Ntro.time().sleep(EXECUTABLE_TASK_GRAPH_SLEEP_TIME_MILISECONDS);
		}
		
		if(timeout()) {

			result.registerException(new TimeoutException());

		}else if(hasException()) {

			result.registerException(exception());

		}else {
			
			result.registerValue((ObjectMap) this);
			
		}

		return result;
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
	
	
	private boolean hasException() {
		return tasks().ifSome(task -> taskHasException(task));
	}

	private Throwable exception() {
		return tasks().reduceToResult((Throwable) null, (accumulator, task) -> {
			if(accumulator != null) {
				throw new Break();
			}
			
			accumulator = findFirstException(task);
			
			return accumulator;
					
		}).value();
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
