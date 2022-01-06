package ca.aiguilleur.frontend.queue;

import static ca.ntro.app.frontend.tasks.Factory.*;

import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;
import ca.ntro.app.models.ModelUpdates;

public class QueueController {

	public static void createTasks(FrontendTaskCreator to) {

		displayQueue(to);

	}

	private static void displayQueue(FrontendTaskCreator to) {

		to.implement(task("displayQueue"))
		
		  .waitFor(view(QueueView.class))
		  
		  .waitFor(modelUpdates(QueueModel.class))
		  
		  .thenExecuteAsync((inputs, notify) -> {
			  
			  QueueView    view    = inputs.get(view(QueueView.class));
			  ModelUpdates updates = inputs.get(modelUpdates(QueueModel.class));
			  
			  view.displayModelUpdates(updates);
		  });
	}

}
