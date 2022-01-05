package ca.aiguilleur.frontend.pages;

import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.messages.MsgDisplayPong;
import ca.aiguilleur.messages.MsgDisplayQueue;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

public class PagesController {

	public static void createTasks(FrontendTasks inOrder) {

		createQueueView(inOrder);
		createPongView(inOrder);
		

		inOrder.whenExists(viewInstalled(PagesView.class))
		       .and(viewCreated(QueueView.class))
		       .and(messageReceived(MsgDisplayQueue.class))
		       .execute((inputs, outputs) -> {
		    	   
		    	   PagesView view = inputs.getView(PagesView.class);
		    	   QueueView queueView = inputs.getCreatedView(QueueView.class);
		    	   
		    	   view.displaySubView(queueView);
		    	   
		    	   outputs.notifyViewDisplayed(queueView);
		       });

		inOrder.whenExists(viewInstalled(PagesView.class))
		       .and(viewCreated(PongView.class))
		       .and(messageReceived(MsgDisplayPong.class))
		       .execute((inputs, outputs) -> {
		    	   
		    	   PagesView view = inputs.getView(PagesView.class);
		    	   PongView pongView = inputs.getCreatedView(PongView.class);
		    	   
		    	   view.displaySubView(pongView);
		       });
	}

	private static void createPongView(FrontendTasks inOrder) {

		inOrder.create("createPongView")

		       .execute((inputs, outputs) -> {

		    	   PongView pongView = inputs.getViewLoader(PongView.class).createView();
		    	   outputs.registerView(PongView.class, pongView);

		       })
		
		       .whenExists(viewLoaded(PongView.class))
		       
		       .onCancel(() -> {
		    	   
		    	   
		       })
		       
		       .onFailure(exception -> {
		    	   
		    	   
		       });
	}

	private static void createQueueView(FrontendTasks inOrder) {
		inOrder.create("createQueueView")

		       .execute((inputs, outputs) -> {

		    	   QueueView queueView = inputs.getViewLoader(QueueView.class).createView();
		    	   outputs.registerView(QueueView.class, queueView);

		       })

		       .whenExists(viewLoaded(QueueView.class));
	}
}
