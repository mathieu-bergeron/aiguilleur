package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.task_graphs.task_graph.TaskNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ObjectMapNtro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class      ExecutableTaskNtro 

       extends    TaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTask {
	
	
	private Map<String, ExecutableAtomicTaskNtro> started = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> suspended = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> resumed = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> stopped = new HashMap<>();
	

	@Override
	public boolean execute(ResultNtro<ObjectMapNtro> result) {
		boolean hasChanged = false;
		
		if(!areEntryTasksDone()) {
			
			hasChanged = hasChanged || entryTasks().reduceToResult(false, (accumulator, entryTask) -> {

				if(!started.containsKey(entryTask.id().toKey().toString())) {
					
					started.put(entryTask.id().toKey().toString(), entryTask);

					accumulator = accumulator || entryTask.start(result);
				}
				
				return accumulator;
				
			}).value();
					
		}else {

			exitTasks().forEach(entryTask -> {
				
				
			});
		}

		return hasChanged;
	}

	@Override
	public boolean suspend(ResultNtro<ObjectMapNtro> result) {
		return false;
	}

	@Override
	public boolean stop(ResultNtro<ObjectMapNtro> result) {
		return false;
	}

}
