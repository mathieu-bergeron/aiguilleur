package ca.ntro.app.frontend.tasks;

public interface FrontendExecutor {

	void execute(FrontendTaskInputs inputs, FrontendTaskNotifyer notify);

}
