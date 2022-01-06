package ca.ntro.app.frontend.tasks;

public interface FrontendTaskNotifyer {
	
	FrontendTaskCreator to();

	void created(Object result);

}
