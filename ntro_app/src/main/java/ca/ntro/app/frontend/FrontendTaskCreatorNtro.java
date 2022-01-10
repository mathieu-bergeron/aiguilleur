package ca.ntro.app.frontend;

import ca.ntro.app.frontend.events.EventNtro;
import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.app.frontend.tasks.BlockingFrontendExecutor;
import ca.ntro.app.frontend.tasks.FrontendExecutor;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptor;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptorNtro;
import ca.ntro.app.services.Window;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskCondition;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskEventHandler;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskMessageHandler;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.wrappers.future.ExceptionHandler;

import static ca.ntro.app.frontend.tasks.Factory.*;

import ca.ntro.app.NtroApp;

public class FrontendTaskCreatorNtro implements FrontendTaskCreator {
	
	
	private TaskGraphNtro taskGraph = (TaskGraphNtro) TaskGraph.newGraph();
	
	// FIXME: not that simple
	//        we need a stack of currentTasks
	//        as taskGroup/addSubTask is hierarchical
	private Task currentTask;

	public TaskGraphNtro getTaskGraph() {
		return taskGraph;
	}

	public void setTaskGraph(TaskGraphNtro taskGraph) {
		this.taskGraph = taskGraph;
	}
	
	
	

	public void executeTasks() {
		getTaskGraph().setGraphName("frontend");
		getTaskGraph().write(Ntro.graphWriter());
		getTaskGraph().execute(new FrontendTaskGraphOptions());
	}
	

	public void addWindowTask(Window window) {
		create(window())
		.thenExecute(inputs -> {
			return window;
		});
	}

	@Override
	public <R> TypedFrontendTaskCreator<R> create(TypedFrontendTaskDescriptor<R> task) {
		return new TypedFrontendTaskCreatorNtro<R>(getTaskGraph(), task);
	}

	@Override
	public FrontendTaskCreator implement(TypedFrontendTaskDescriptor<?> task) {

		((TypedFrontendTaskDescriptorNtro<?>) task).setTaskGraph(getTaskGraph());

		currentTask = getTaskGraph().addTask(task.id());
		
		return this;
	}

	@Override
	public FrontendTaskCreator waitFor(TypedFrontendTaskDescriptor<?> task) {

		Task waitFor = currentTask.addPreviousTask(task.id());
		
		// FIXME: 
		if(task.id().toKey().toString().contains("event[")) {
			
			AtomicTask eventHandler = waitFor.addEntryTask(task.id().toKey().toString(), AtomicTaskEventHandler.class);
			
			// FIXME: TaskDescriptor must save eventClass
			NtroApp.events().registerEventHandler(EventNtro.class, eventHandler);
		}
		
		
		return this;
	}

	@Override
	public FrontendTaskCreator executeAsync(FrontendExecutor executor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontendTaskCreator thenExecuteAsync(FrontendExecutor executor) {
		return null;
	}

	@Override
	public FrontendTaskCreator onCancel(FrontendCancelHandler cancelHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontendTaskCreator onFailure(ExceptionHandler exceptionHandler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypedFrontendTaskDescriptor<?> getTask() {
		TypedFrontendTaskDescriptorNtro<?> descriptor = new TypedFrontendTaskDescriptorNtro<>();

		descriptor.setId(currentTask.id());
		((TypedFrontendTaskDescriptorNtro<?>) descriptor).setTaskGraph(getTaskGraph());

		return descriptor;
	}

	@Override
	public FrontendTaskCreator execute(BlockingFrontendExecutor executor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontendTaskCreator thenExecute(BlockingFrontendExecutor executor) {
		AtomicTask entry = currentTask.addEntryTask(currentTask.id().toKey().toString(), AtomicTaskEventHandler.class);
		
		entry.execute((inputs, notify) -> {
			executor.execute(new FrontendTaskInputsNtro(inputs));

			notify.addResult(true);
		});

		return this;
	}

	@Override
	public FrontendTaskCreator define(TypedFrontendTaskDescriptor<?> task) {
		currentTask = getTaskGraph().addTask(task.id());

		return this;
	}

	@Override
	public FrontendTaskCreator addSubTask(TypedFrontendTaskDescriptor<?> task) {

		Task subTask = getTaskGraph().findTask(task.id());

		currentTask.addSubTask(subTask);
		
		subTask.previousTasks().forEach(previousTask -> {
			currentTask.addSubTask(previousTask);
		});

	
		return this;
	}



}
