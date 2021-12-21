package ca.ntro.core.task_graphs.executable_task_graph;



import ca.ntro.core.task_graphs.task_graph.TaskNtro;
import ca.ntro.core.values.ObjectMap;

public class      ExecutableTaskNtro 

       extends    TaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTask {
	

	
	
	public void continueExecution(ObjectMap results) {
		if(isInProgress()) {
			executeEntryTasks(results);
		}
		
		if(areSubTasksDone()) {
			executeExitTasks(results);
		}
	}
	

	private void executeEntryTasks(ObjectMap results) {
		entryTasks().forEach(entryTask -> {
			
			entryTask.execute(results);
		});
	}

	private void executeExitTasks(ObjectMap results) {
		exitTasks().forEach(exitTask -> {
			
			exitTask.execute(results);
		});
	}


	private void cancelEntryTasks(ObjectMap results) {
		entryTasks().forEach(entryTask -> {
			
			entryTask.cancel(results);
		});
	}

	private void cancelExitTasks(ObjectMap results) {
		exitTasks().forEach(exitTasks -> {
			
			exitTasks.cancel(results);
		});
	}
}
