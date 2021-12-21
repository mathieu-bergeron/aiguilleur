package ca.aiguilleur.frontend.pages;

import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.messages.DisplayPongMessage;
import ca.aiguilleur.messages.DisplayQueueMessage;
import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.TaskCreator;

import static ca.ntro.app.frontend.controllers.tasks.TaskCreator.*;

public class PagesController implements Controller {

	@Override
	public void createTasks(TaskCreator creator) {

		creator.when(viewLoaded(QueueView.class))
		       .execute(results -> {

		    	   QueueView queueView = results.getViewLoader(QueueView.class).createView();
		    	   results.registerView(QueueView.class, queueView);
		       })
		       .setTaskName("Créer la vue Queue");

		creator.when(viewLoaded(PongView.class))
		       .execute(results -> {

		    	   PongView pongView = results.getViewLoader(PongView.class).createView();
		    	   results.registerView(PongView.class, pongView);
		       });

		creator.when(viewDisplayed(PagesView.class))
		       .and(viewCreated(QueueView.class))
		       .and(messageReceived(DisplayQueueMessage.class))
		       .execute(results -> {
		    	   
		    	   PagesView view = results.getDisplayedView(PagesView.class);
		    	   QueueView queueView = results.getCreatedView(QueueView.class);
		    	   
		    	   view.displaySubView(queueView);
		       });

		creator.when(viewDisplayed(PagesView.class))
		       .and(viewCreated(PongView.class))
		       .and(messageReceived(DisplayPongMessage.class))
		       .execute(results -> {
		    	   
		    	   PagesView view = results.getDisplayedView(PagesView.class);
		    	   PongView pongView = results.getCreatedView(PongView.class);
		    	   
		    	   view.displaySubView(pongView);
		       });
	}

}
