package ca.ntro.app.backend.handlers;


public interface BackendResults {

	<O extends Object> O get(BackendTaskDescriptor<O> event);

}
