package ca.ntro.app.frontend;

import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.app.frontend.tasks.BlockingFrontendExecutor;
import ca.ntro.app.frontend.tasks.FrontendExecutor;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptor;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptorNtro;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskCondition;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.wrappers.future.ExceptionHandler;

import static ca.ntro.app.frontend.tasks.Factory.*;

public class FrontendTaskCreatorNtro implements FrontendTaskCreator {
	
	private TaskGraphNtro taskGraph = (TaskGraphNtro) TaskGraph.newGraph();
	
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
		
		currentTask.addPreviousTask(task.id());
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontendTaskCreator execute(BlockingFrontendExecutor executor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontendTaskCreator thenExecute(BlockingFrontendExecutor executor) {
		AtomicTask entry = currentTask.addEntryTask(currentTask.id().toKey().toString(), AtomicTaskCondition.class);
		
		entry.execute((inputs, notify) -> {
			executor.execute(new FrontendTaskInputsNtro(inputs));

			notify.addResult(true);
		});

		return this;
	}

	@Override
	public FrontendTaskCreator define(TypedFrontendTaskDescriptor<?> task) {
		// TODO Auto-generated method stub
		return null;
	}


}
