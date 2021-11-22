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
		
		creator.when(viewDisplayed(PagesView.class))
		       .and(viewLoaded(QueueView.class))
		       .and(messageReceived(DisplayQueueMessage.class))
		       .execute(results -> {
		    	   
		    	   PagesView view = results.getDisplayedView(PagesView.class);
		    	   QueueView queueView = results.getViewLoader(QueueView.class).createView();
		    	   
		    	   view.displaySubView(queueView);
		       });

		creator.when(viewDisplayed(PagesView.class))
		       .and(viewLoaded(PongView.class))
		       .and(messageReceived(DisplayPongMessage.class))
		       .execute(results -> {
		    	   
		    	   PagesView view = results.getDisplayedView(PagesView.class);
		    	   PongView pongView = results.getViewLoader(PongView.class).createView();
		    	   
		    	   view.displaySubView(pongView);
		       });
	}

}
