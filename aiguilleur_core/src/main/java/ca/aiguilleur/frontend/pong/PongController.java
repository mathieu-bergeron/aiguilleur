package ca.aiguilleur.frontend.pong;

import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.FrontendTask;
import ca.ntro.app.frontend.controllers.tasks.FrontendTaskNull;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

import ca.aiguilleur.messages.MsgDisplayPong;

public class PongController implements Controller {
	
	private FrontendTask displayGameSubTask = new FrontendTaskNull();

	@Override
	public void createTasks(FrontendTasks inOrder) {
		displayCurrentGame(inOrder);
	}

	private void displayCurrentGame(FrontendTasks inOrder) {
		
		inOrder.to("displayCurrentGame")
		       .execute((inputs, outputs) -> {

				   MsgDisplayPong message = inputs.getMessage(MsgDisplayPong.class);
				   ModelId modelId = message.getModelId();
				   
				   displayGameSubTask.cancel(); // XXX: removes the task from the TaskGraph
				   
				   displayGameSubTask = displayGameByModelId(outputs.inOrder(), modelId);
		       })
		       
		       .when(messageReceived(MsgDisplayPong.class))
		       
		       .onCancel(() -> {
		    	   
		    	   
		       })
		       
		       .onFailure(exception -> {
		    	   
		    	   
		       });
	}

	private FrontendTask displayGameByModelId(FrontendTasks inOrder, ModelId modelId) {

		return inOrder.to("displayGameByModelId")
				      .execute((inputs, outputs) -> {

						   PongView view = inputs.getDisplayedView(PongView.class);
						   ModelUpdates updates = inputs.getModelUpdates(modelId);

						   view.displayModelUpdates(updates);

					   })
		
					   .when(modelObserved(modelId))
					
					   .onCancel(() -> {
						
						
					   })
					
					   .onFailure(exception2 -> {
						
						
					   })

					   .getTask();
	}

}
