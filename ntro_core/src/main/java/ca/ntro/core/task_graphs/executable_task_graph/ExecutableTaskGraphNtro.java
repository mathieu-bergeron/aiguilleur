package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.identifyers.Id;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.values.MutableObjectMap;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ObjectMapNtro;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.future.FutureNtro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ExecutableTaskGraphNtro

       extends TaskGraphNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTaskGraph, MutableObjectMap {
	
	public static final long EXECUTABLE_TASK_GRAPH_SLEEP_TIME_MILISECONDS = 10; // FIXME
	public static final long EXECUTABLE_TASK_GRAPH_TIMEOUT_MILISECONDS = 300;   // FIXME

	private Map<String, ExecutableTaskNtro> blocked = new HashMap<>();
	private Map<String, ExecutableTaskNtro> inProgress = new HashMap<>();
	private Map<String, ExecutableTaskNtro> done = new HashMap<>();
	
	private long lastChange = 0;

	@Override
	public Future<ObjectMap> execute() {

		return new FutureNtro<>();
	}
	
	private void startExecuting(ResultNtro<ObjectMapNtro> result) {
		tasks().forEach(task -> {
			if(result.hasException()) {
				throw new Break();
			}

			if(task.isBlocked()) {
				
				blocked.put(task.id().toKey().toString(), task);
				
			}else if(task.isInProgress()) {
				
				inProgress.put(task.id().toKey().toString(), task);
				task.execute(result);
				
			}else if(task.isDone()) {
				
				done.put(task.id().toKey().toString(), task);
			}
		});
		
		lastChange = Ntro.time().nowMilliseconds();
	}
	
	private boolean timeout() {
		return (Ntro.time().nowMilliseconds() - lastChange) > EXECUTABLE_TASK_GRAPH_TIMEOUT_MILISECONDS;
	}

	private void resumeExecuting(ResultNtro<ObjectMapNtro> result) {
		Map<String, ExecutableTaskNtro> newBlocked = new HashMap<>();
		Map<String, ExecutableTaskNtro> newInProgress = new HashMap<>();
		Map<String, ExecutableTaskNtro> newDone = new HashMap<>();
		
		boolean hasChanged = processBlockedTasks(result, newBlocked, newInProgress, newDone);
		hasChanged = hasChanged || processInProgressTasks(result, newBlocked, newInProgress, newDone);
		hasChanged = hasChanged || processDoneTasks(result, newBlocked, newInProgress, newDone);
		
		if(hasChanged) {
			lastChange = Ntro.time().nowMilliseconds();
		}
		
		blocked = newBlocked;
		inProgress = newInProgress;
		done = newDone;
	}

	private boolean processBlockedTasks(ResultNtro<ObjectMapNtro> result, 
			                            Map<String, ExecutableTaskNtro> newBlocked, 
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
				hasChanged = hasChanged || task.execute(result);
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
				hasChanged = hasChanged || task.stop(result);
			}
		}
		
		return hasChanged;
	}

	private boolean processInProgressTasks(ResultNtro<ObjectMapNtro> result, 
			                               Map<String, ExecutableTaskNtro> newBlocked, 
			                               Map<String, ExecutableTaskNtro> newInProgress, 
			                               Map<String, ExecutableTaskNtro> newDone) {
		
		boolean hasChanged = false;

		for(Map.Entry<String, ExecutableTaskNtro> entry : inProgress.entrySet()) {
			
			String taskId = entry.getKey();
			ExecutableTaskNtro task = entry.getValue();

			if(task.isBlocked()) {
				
				newBlocked.put(taskId, task);
				hasChanged = hasChanged || task.suspend(result);
				
			}else if(task.isInProgress()) {
				
				newInProgress.put(taskId, task);
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
				hasChanged = hasChanged || task.stop(result);
			}
		}
		
		return hasChanged;
	}

	private boolean processDoneTasks(ResultNtro<ObjectMapNtro> result, 
			                         Map<String, ExecutableTaskNtro> newBlocked, 
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
				hasChanged = hasChanged || task.execute(result);
				
			}else if(task.isDone()) {

				newDone.put(taskId, task);
			}
		}
		
		return hasChanged;
	}
	
	@Override
	public Result<ObjectMap> executeBlocking() {
		ResultNtro<ObjectMapNtro> result = new ResultNtro<>(new ObjectMapNtro());
		
		startExecuting(result);

		while(!inProgress.isEmpty()
				&& !timeout()
				&& !result.hasException()) {
			
			resumeExecuting(result);

			Ntro.time().sleep(EXECUTABLE_TASK_GRAPH_SLEEP_TIME_MILISECONDS);
		}
		
		if(timeout()) {
			result.registerException(new TimeoutException());
		}
		
		return new ResultNtro<ObjectMap>(result.value());
	}

	@Override
	public <O> O getObject(Class<O> _class, Id id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> O getObject(Class<O> _class, String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerObject(String id, Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerObject(Id id, Object object) {
		// TODO Auto-generated method stub
		
	}

}
