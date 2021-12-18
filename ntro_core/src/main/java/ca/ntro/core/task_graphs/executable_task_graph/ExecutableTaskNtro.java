package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.core.task_graphs.ObjectMapReader;
import ca.ntro.core.task_graphs.TaskObjectMap;
import ca.ntro.core.task_graphs.task_graph.TaskNtro;
import ca.ntro.core.values.ObjectMap;

public class      ExecutableTaskNtro 

       extends    TaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTask {
	
	/* TODO: keeping a ObjectMapReader for every previousTask and for the parentTask (if any)
	 *       we can execute if previousResults.forAll(pr -> pr.hasNext());
	 *       
	 *       we execute on the objectMap = mergeAll(previousResults);
	 * 
	 */
	private Map<String, ObjectMapReader> previousResults = new HashMap<>();
	
	private Map<String, ExecutableAtomicTaskNtro> started = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> suspended = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> resumed = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> stopped = new HashMap<>();

	@Override
	public boolean isInProgress() {
		return !isBlocked() 
				&& previousResults.ifAll(pr -> pr.hasNext());
	}
	


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

	public void suspend() {
		//throw new RuntimeException("TODO");
	}

	public void stop() {
		//throw new RuntimeException("TODO");
	}

}
