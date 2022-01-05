package ca.aiguilleur.frontend.queue;

import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

public class QueueController {

	public static void createTasks(FrontendTasks inOrder) {

		displayQueue(inOrder);

	}

	private static void displayQueue(FrontendTasks inOrder) {

		inOrder.create("displayQueue")

		       .execute((results, outputs) -> {

		    	 QueueView view = results.getView(QueueView.class);
		    	 view.displayModelUpdates(results.getModelUpdates(QueueModel.id()));
		    	 
		       })

			   .whenExists(viewInstalled(QueueView.class))

		       .and(modelObserved(QueueModel.id()))
		       
		       .onCancel(() -> {
		    	   
		    	   
		    	   
		       })
		       
		       .onFailure(exception -> {
		    	   
		    	   
		       });
	}

}
