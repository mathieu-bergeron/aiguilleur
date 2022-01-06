package ca.ntro.app.frontend.tasks;

public interface TypedFrontendTaskDescriptor<O extends Object> {

	void cancel();
	void destroy();

}
