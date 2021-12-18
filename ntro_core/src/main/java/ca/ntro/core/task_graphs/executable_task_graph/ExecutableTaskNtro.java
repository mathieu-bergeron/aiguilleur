package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.core.task_graphs.TaskObjectMap;
import ca.ntro.core.task_graphs.task_graph.TaskNtro;
import ca.ntro.core.values.ObjectMap;

public class      ExecutableTaskNtro 

       extends    TaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTask {
	
	private List<TaskObjectMap> queuedResults = new ArrayList<>();
	
	
	private Map<String, ExecutableAtomicTaskNtro> started = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> suspended = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> resumed = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> stopped = new HashMap<>();

	@Override
	public boolean isInProgress() {
		/* TODO: we keep a queue of results.
		 *       every previous tasks contributes to it.
		 *       we are ready to execute when
		 *       we have at least one "Stack" of results
		 *       for which every previousTask (and the parentTask)
		 *       has contributed.
		 */
		return isBlocked() && !queuedResults.isEmpty()
				&& isComplete(queuedResults.get(0));
	}
	

	/* isComplete if every previousTask (and the parentTask)
	 * has contributed its result to this ObjectMap
	 */
	private boolean isComplete(ObjectMap objectMap) {
		return false;
	}


	@Override
	public void execute() {
		
		if(!areEntryTasksDone()) {
			
			entryTasks().forEach(entryTask -> {
				
				if(!started.containsKey(entryTask.id().toKey().toString())) {
					started.put(entryTask.id().toKey().toString(), entryTask);

					entryTask.start();
				}
			});
					
		}else if(areSubTasksDone()
				&& !areExitTasksDone()) {

			exitTasks().forEach(exitTask -> {

				if(!started.containsKey(exitTask.id().toKey().toString())) {
					started.put(exitTask.id().toKey().toString(), exitTask);

					exitTask.start();
				}
			});
		}
	}

	@Override
	public void suspend() {
		//throw new RuntimeException("TODO");
	}

	@Override
	public void stop() {
		//throw new RuntimeException("TODO");
	}

}
