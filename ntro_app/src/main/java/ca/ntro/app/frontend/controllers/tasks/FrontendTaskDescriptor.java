package ca.ntro.app.frontend.controllers.tasks;

public interface FrontendTaskDescriptor<O extends Object> {

	void cancel();
	void destroy();

}
