package ca.ntro.core.task_graphs.executable_task_graph;



import ca.ntro.core.task_graphs.task_graph.TaskNtro;
import ca.ntro.core.values.ObjectMap;

import static ca.ntro.core.task_graphs.executable_task_graph.ExecutableTaskState.*;

public class      ExecutableTaskNtro 

       extends    TaskNtro<ExecutableTaskNtro, ExecutableAtomicTaskNtro>

       implements ExecutableTask {
	

	private ExecutableTaskState previousState;

	public ExecutableTaskState getPreviousState() {
		return previousState;
	}

	public void setPreviousState(ExecutableTaskState previousState) {
		this.previousState = previousState;
	}
	
	
	
	public void continueExecution(ObjectMap results) {
		ExecutableTaskState previousState = getPreviousState();
		ExecutableTaskState currentState = currentState();
		
		if(previousState == EXECUTING_ENTRY_TASKS
				&& currentState != EXECUTING_ENTRY_TASKS) {
			
			cancelEntryTasks(results);

		}else if(previousState == EXECUTING_EXIT_TASKS
				&& currentState != EXECUTING_EXIT_TASKS) {
			
			cancelEntryTasks(results);
		}

		
		if(currentState == EXECUTING_ENTRY_TASKS) {

			executeEntryTasks(results);

		}else if(currentState == EXECUTING_EXIT_TASKS) {

			executeExitTasks(results);
		}
		
		
		memorizeState();
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

	public void prepareExecution() {
		memorizeState();
	}
	
	private void memorizeState() {
		setPreviousState(currentState());
	}

	private ExecutableTaskState currentState() {
		ExecutableTaskState currentState = IDLE;

		if(isInProgress()
				&& !areSubTasksDone()) {
			
			currentState = EXECUTING_ENTRY_TASKS;

		} else if(isInProgress()
				&& areSubTasksDone()) {
			
			currentState = EXECUTING_EXIT_TASKS;
		}
		
		return currentState;
	}

}
