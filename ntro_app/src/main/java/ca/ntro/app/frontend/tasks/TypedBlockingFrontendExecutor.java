package ca.ntro.app.frontend.tasks;

public interface TypedBlockingFrontendExecutor<R extends Object> {

	R execute(FrontendTaskInputs inputs);

}
