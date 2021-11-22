package ca.aiguilleur.frontend.queue;

import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.frontend.controllers.tasks.TaskExecutor;
import ca.ntro.app.frontend.controllers.tasks.TaskResults;
import ca.ntro.app.models.ModelUpdate;
import ca.ntro.app.models.ModelUpdates;

public class QueueDisplayTask implements TaskExecutor {
	
	@Override
	public void execute(TaskResults results) {
		
		QueueView view = results.getDisplayedView(QueueView.class);
		ModelUpdates updates = results.getModelUpdates(QueueModel.id());
		
		view.displayModelUpdates(updates);
	}
}
