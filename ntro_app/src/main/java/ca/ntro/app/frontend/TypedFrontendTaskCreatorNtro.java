package ca.ntro.app.frontend;

import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.app.frontend.tasks.TypedBlockingFrontendExecutor;
import ca.ntro.app.frontend.tasks.TypedFrontendExecutor;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptor;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptorNtro;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public class TypedFrontendTaskCreatorNtro<O extends Object> 

       implements TypedFrontendTaskCreator<O> {

	private TaskGraphNtro taskGraph;
	private Task currentTask;
	
	public TaskGraphNtro getTaskGraph() {
		return taskGraph;
	}

	public void setTaskGraph(TaskGraphNtro taskGraph) {
		this.taskGraph = taskGraph;
	}

	public Task getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}

	public TypedFrontendTaskCreatorNtro(TaskGraphNtro taskGraph, TypedFrontendTaskDescriptor<?> task) {
		this.taskGraph = taskGraph;
		this.currentTask = taskGraph.addTask(task.id());
	}

	@Override
	public TypedFrontendTaskCreator<O> waitFor(TypedFrontendTaskDescriptor<?> task) {
		
		if(taskGraph.findTask(task.id()) == null) {
			throw new RuntimeException("Task does not exist: " + task.id().toKey());
		}

		currentTask.addPreviousTask(task.id());

		return this;
	}

	@Override
	public TypedFrontendTaskCreator<O> execute(TypedBlockingFrontendExecutor<O> executor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedFrontendTaskCreator<O> thenExecute(TypedBlockingFrontendExecutor<O> executor) {
		AtomicTask entry = currentTask.addEntryTask(currentTask.id().toKey().toString());
		
		entry.execute((inputs, notify) -> {
			
			O result = executor.execute(new FrontendTaskInputsNtro(inputs));
			
			notify.addResult(result);
		});
		
		return this;
	}

	@Override
	public TypedFrontendTaskCreator<O> executeAsync(TypedFrontendExecutor<O> executor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedFrontendTaskCreator<O> thenExecuteAsync(TypedFrontendExecutor<O> executor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedFrontendTaskCreator<O> onCancel(FrontendCancelHandler cancelHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedFrontendTaskCreator<O> onFailure(ExceptionHandler exceptionHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedFrontendTaskDescriptor<O> getTask() {
		TypedFrontendTaskDescriptorNtro<O> descriptor = new TypedFrontendTaskDescriptorNtro<>();

		descriptor.setId(currentTask.id());
		((TypedFrontendTaskDescriptorNtro<?>) descriptor).setTaskGraph(getTaskGraph());

		return descriptor;
	}
}
