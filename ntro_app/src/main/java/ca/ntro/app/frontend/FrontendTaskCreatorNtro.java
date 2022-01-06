package ca.ntro.app.frontend;

import ca.ntro.app.frontend.handlers.FrontendCancelHandler;
import ca.ntro.app.frontend.tasks.FrontendExecutor;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptor;
import ca.ntro.core.wrappers.future.ExceptionHandler;

public class FrontendTaskCreatorNtro implements FrontendTaskCreator {
	
	public void executeTasks() {
		throw new RuntimeException("TODO");
	}

	public void addWindowTask(Window window) {

	}

	@Override
	public <R> TypedFrontendTaskCreator<R> create(TypedFrontendTaskDescriptor<R> task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontendTaskCreator implement(TypedFrontendTaskDescriptor<?> task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontendTaskCreator waitFor(TypedFrontendTaskDescriptor<?> task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontendTaskCreator execute(FrontendExecutor executor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FrontendTaskCreator thenExecute(FrontendExecutor executor) {
		// TODO Auto-generated method stub
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


}
