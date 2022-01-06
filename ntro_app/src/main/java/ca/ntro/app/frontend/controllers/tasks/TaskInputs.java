package ca.ntro.app.frontend.controllers.tasks;


public interface TaskInputs {

	<O extends Object> O get(FrontendTaskDescriptor<O> task);


}
