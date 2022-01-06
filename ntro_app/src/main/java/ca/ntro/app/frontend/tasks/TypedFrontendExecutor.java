package ca.ntro.app.frontend.tasks;

public interface TypedFrontendExecutor<R extends Object> {

	void execute(FrontendTaskInputs inputs, TypedFrontendTaskNotifyer<R> notify);

}
