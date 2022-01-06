package ca.ntro.app.frontend.tasks;

public interface TypedFrontendTaskNotifyer<R extends Object> {
	
	FrontendTaskCreator to();

	void created(R result);

}
