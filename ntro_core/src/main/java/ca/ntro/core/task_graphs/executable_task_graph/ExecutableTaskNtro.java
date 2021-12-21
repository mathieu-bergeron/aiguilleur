package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.task_graphs.ObjectMapReader;
import ca.ntro.core.task_graphs.task_graph.TaskNtro;
import ca.ntro.core.values.ObjectMap;

public class      ExecutableTaskNtro 

       extends    TaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTask {
	
	private Map<String, ExecutableAtomicTaskNtro> blocked = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> inProgress = new HashMap<>();

	public void execute() {
		
		if(!areEntryTasksDone()) {
			
			entryTasks().forEach(entryTask -> {
				
				if(!inProgress.containsKey(entryTask.id().toKey().toString())) {
					inProgress.put(entryTask.id().toKey().toString(), entryTask);

					entryTask.execute(results());
				}
			});
					
		}else if(areSubTasksDone()
				&& !areExitTasksDone()) {

			exitTasks().forEach(exitTask -> {

				if(!inProgress.containsKey(exitTask.id().toKey().toString())) {
					inProgress.put(exitTask.id().toKey().toString(), exitTask);

					exitTask.execute(results());
				}
			});
		}
	}


	public void cancel() {
		//throw new RuntimeException("TODO");
	}


	@Override
	public ObjectMapReader newResultsReader() {
		// TODO Auto-generated method stub
		return null;
	}



}
