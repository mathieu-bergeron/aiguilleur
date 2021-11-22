package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;
import ca.ntro.app.messages.Message;
import ca.ntro.core.graphs.task_graph.Task;
import ca.ntro.core.identifyers.ModelId;

public interface TaskCreator {

	TaskCreator when();
	TaskCreator and();
	TaskCreator viewLoaded();
	TaskCreator modelObserved(ModelId id);
	TaskCreator subViewLoaded(Class<? extends View> viewClass);
	TaskCreator messageReceived(Class<? extends Message> messageClass);

	Task execute(TaskExecutor executor);
}
