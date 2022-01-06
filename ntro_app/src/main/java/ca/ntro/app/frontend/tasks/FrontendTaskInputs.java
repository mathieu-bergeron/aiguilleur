package ca.ntro.app.frontend.tasks;


public interface FrontendTaskInputs {

	<O extends Object> O get(TypedFrontendTaskDescriptor<O> task);


}
