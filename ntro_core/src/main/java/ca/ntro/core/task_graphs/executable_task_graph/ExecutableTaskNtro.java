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
	
	private Map<String, ExecutableAtomicTaskNtro> blocked = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> inProgress = new HashMap<>();

	@Override
	public boolean isInProgress() {
		return !isWaiting() 
				&& previousResults.ifAll(pr -> pr.hasNext());
	}


	public void execute() {
		
		if(!areEntryTasksDone()) {
			
			entryTasks().forEach(entryTask -> {
				
				if(!inProgress.containsKey(entryTask.id().toKey().toString())) {
					inProgress.put(entryTask.id().toKey().toString(), entryTask);

					entryTask.execute();
				}
			});
					
		}else if(areSubTasksDone()
				&& !areExitTasksDone()) {

			exitTasks().forEach(exitTask -> {

				if(!inProgress.containsKey(exitTask.id().toKey().toString())) {
					inProgress.put(exitTask.id().toKey().toString(), exitTask);

					exitTask.execute();
				}
			});
		}
	}


	public void cancel() {
		//throw new RuntimeException("TODO");
	}

}
