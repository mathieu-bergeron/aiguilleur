package ca.aiguilleur.frontend.queue;

import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

public class QueueController implements Controller {

	@Override
	public void createTasks(FrontendTasks inOrder) {

		observeQueueModel(inOrder);

	}

	private void observeQueueModel(FrontendTasks inOrder) {

		inOrder.to("observeQueueModel")
		         .execute(results -> {

		    	   QueueView view = results.getDisplayedView(QueueView.class);
		    	   view.displayModelUpdates(results.getModelUpdates(QueueModel.id()));

		         })

				 .when(viewDisplayed(QueueView.class))

		        .and(modelObserved(QueueModel.id()));
	}

}
