package ca.aiguilleur.frontend.pong;

import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.TaskCreator;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;

import static ca.ntro.app.frontend.controllers.tasks.TaskCreator.*;

import ca.aiguilleur.messages.DisplayPongMessage;

public class PongController implements Controller {
	
	private ModelId lastModelId = null;

	@Override
	public void createTasks(TaskCreator creator) {
		
		creator.when(messageReceived(DisplayPongMessage.class))
			   .execute(results -> {
				   
				   DisplayPongMessage message = results.getMessage(DisplayPongMessage.class);
				   ModelId modelId = message.getModelId();
				   
				   if(!modelId.equals(lastModelId)) {
					   
					   creator.removeModelObserver(lastModelId);
					   lastModelId = modelId;

					   creator.when(viewDisplayed(PongView.class))
							  .and(modelObserved(modelId))
							  .execute( __ -> {
								  
								  PongView view = results.getDisplayedView(PongView.class);
								  ModelUpdates updates = results.getModelUpdates(message.getModelId());
								  
								  view.displayModelUpdates(updates);
							  });
				   }
			   });
	}

}
