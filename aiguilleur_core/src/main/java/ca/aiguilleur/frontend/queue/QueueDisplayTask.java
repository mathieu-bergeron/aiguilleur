package ca.aiguilleur.frontend.queue;

import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.frontend.controllers.tasks.FrontendExecutor;
import ca.ntro.app.frontend.controllers.tasks.TaskInputs;
import ca.ntro.app.frontend.controllers.tasks.TaskOutputs;
import ca.ntro.app.models.ModelUpdates;

public class QueueDisplayTask implements FrontendExecutor {
	
	@Override
	public void execute(TaskInputs results, TaskOutputs outputs) {
		
		QueueView view = results.getView(QueueView.class);
		ModelUpdates updates = results.getModelUpdates(QueueModel.id());
		
		view.displayModelUpdates(updates);
	}
}
