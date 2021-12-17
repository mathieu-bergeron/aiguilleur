package ca.ntro.core.task_graphs.executable_task_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.task_graphs.task_graph.TaskNtro;

public class      ExecutableTaskNtro 

       extends    TaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTask {
	
	
	private Map<String, ExecutableAtomicTaskNtro> started = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> suspended = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> resumed = new HashMap<>();
	private Map<String, ExecutableAtomicTaskNtro> stopped = new HashMap<>();
	

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
