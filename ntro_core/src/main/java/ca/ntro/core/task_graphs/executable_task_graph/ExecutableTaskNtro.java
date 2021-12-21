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
	
	private Map<String, ExecutableAtomicTaskNtro> blocked = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> inProgress = new HashMap<>();

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


	@Override
	public boolean hasResults() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ObjectMap results() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ObjectMapReader newResultsReader() {
		// TODO Auto-generated method stub
		return null;
	}

}
