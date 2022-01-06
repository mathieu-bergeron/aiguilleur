package ca.aiguilleur.frontend.queue;

import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.frontend.controllers.tasks.FrontendTaskCreator;
import ca.ntro.app.models.ModelUpdates;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTaskCreator.*;

public class QueueController {

	public static void createTasks(FrontendTaskCreator to) {

		displayQueue(to);

	}

	private static void displayQueue(FrontendTaskCreator to) {

		to.implement(task("displayQueue"))
		
		  .waitFor(view(QueueView.class))
		  
		  .waitFor(modelUpdates(QueueModel.class))
		  
		  .thenExecute((inputs, notify) -> {
			  
			  QueueView    view    = inputs.get(view(QueueView.class));
			  ModelUpdates updates = inputs.get(modelUpdates(QueueModel.class));
			  
			  view.displayModelUpdates(updates);
		  });
	}

}
