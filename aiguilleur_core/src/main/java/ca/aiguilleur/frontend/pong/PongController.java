package ca.aiguilleur.frontend.pong;

import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

import ca.aiguilleur.messages.MsgDisplayPong;

public class PongController implements Controller {
	
	private ModelId lastModelId = null;

	@Override
	public void createTasks(FrontendTasks creator) {
		
		creator.when(messageReceived(MsgDisplayPong.class))
			   .execute(results -> {
				   
				   MsgDisplayPong message = results.getMessage(MsgDisplayPong.class);
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
