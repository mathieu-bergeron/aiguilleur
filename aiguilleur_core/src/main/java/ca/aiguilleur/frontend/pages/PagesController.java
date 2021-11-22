package ca.aiguilleur.frontend.pages;

import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.messages.DisplayPong;
import ca.aiguilleur.messages.DisplayQueue;
import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.TaskCreator;

public class PagesController implements Controller {

	@Override
	public void createTasks(TaskCreator creator) {
		
		creator.when().viewLoaded()
		       .and().subViewLoaded(QueueView.class)
		       .and().messageReceived(DisplayQueue.class)
		       .execute(results -> {
		    	   
		    	   PagesView view = results.getView(PagesView.class);
		    	   QueueView queueView = results.getView(QueueView.class);
		    	   
		    	   view.displaySubView(queueView);
		       });

		creator.when().viewLoaded()
		       .and().subViewLoaded(PongView.class)
		       .and().messageReceived(DisplayPong.class)
		       .execute(results -> {
		    	   
		    	   PagesView view = results.getView(PagesView.class);
		    	   PongView pongView = results.getView(PongView.class);
		    	   
		    	   view.displaySubView(pongView);
		       });
	}

}
