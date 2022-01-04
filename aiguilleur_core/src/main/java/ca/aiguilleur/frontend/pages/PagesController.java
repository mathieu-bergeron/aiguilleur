package ca.aiguilleur.frontend.pages;

import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.messages.MsgDisplayPong;
import ca.aiguilleur.messages.MsgDisplayQueue;
import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

public class PagesController implements Controller {

	@Override
	public void createTasks(FrontendTasks inOrder) {

		createQueueView(inOrder);
		createPongView(inOrder);
		

		inOrder.when(viewDisplayed(PagesView.class))
		       .and(viewCreated(QueueView.class))
		       .and(messageReceived(MsgDisplayQueue.class))
		       .execute(results -> {
		    	   
		    	   PagesView view = results.getDisplayedView(PagesView.class);
		    	   QueueView queueView = results.getCreatedView(QueueView.class);
		    	   
		    	   view.displaySubView(queueView);
		       });

		inOrder.when(viewDisplayed(PagesView.class))
		       .and(viewCreated(PongView.class))
		       .and(messageReceived(MsgDisplayPong.class))
		       .execute(results -> {
		    	   
		    	   PagesView view = results.getDisplayedView(PagesView.class);
		    	   PongView pongView = results.getCreatedView(PongView.class);
		    	   
		    	   view.displaySubView(pongView);
		       });
	}

	private void createPongView(FrontendTasks inOrder) {

		inOrder.to("createPongView")

		       .execute(results -> {

		    	   PongView pongView = results.getViewLoader(PongView.class).createView();
		    	   results.registerView(PongView.class, pongView);

		       })
		
		       .when(viewLoaded(PongView.class))
		       
		       .onCancel(() -> {
		    	   
		    	   
		       })
		       
		       .onFailure(exception -> {
		    	   
		    	   
		       });
	}

	private void createQueueView(FrontendTasks inOrder) {
		inOrder.to("createQueueView")

		       .execute(results -> {

		    	   QueueView queueView = results.getViewLoader(QueueView.class).createView();
		    	   results.registerView(QueueView.class, queueView);

		       })

		       .when(viewLoaded(QueueView.class));
	}

}
