package ca.aiguilleur.frontend.pong;

import ca.ntro.app.frontend.controllers.tasks.FrontendTask;
import ca.ntro.app.frontend.controllers.tasks.FrontendTaskNull;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

import ca.aiguilleur.messages.MsgDisplayPong;

public class PongController {
	
	private static FrontendTask displayGameSubTask = new FrontendTaskNull();

	public static void createTasks(FrontendTasks inOrder) {
		displayCurrentGame(inOrder);
	}

	private static void displayCurrentGame(FrontendTasks inOrder) {
		
		inOrder.create("displayCurrentGame")
		       .execute((inputs, outputs) -> {

				   MsgDisplayPong message = inputs.getMessage(MsgDisplayPong.class);
				   ModelId modelId = message.getModelId();
				   
				   displayGameSubTask.destroy(); // XXX: removes the task from the TaskGraph
				   
				   displayGameSubTask = displayGameByModelId(outputs.inOrder(), modelId);
		       })
		       
		       .whenExists(messageReceived(MsgDisplayPong.class))
		       
		       .onCancel(() -> {
		    	   
		    	   
		       })
		       
		       .onFailure(exception -> {
		    	   
		    	   
		       });
	}

	private static FrontendTask displayGameByModelId(FrontendTasks inOrder, ModelId modelId) {

		return inOrder.create("displayGameByModelId")
				      .execute((inputs, outputs) -> {

						   PongView view = inputs.getView(PongView.class);
						   ModelUpdates updates = inputs.getModelUpdates(modelId);

						   view.displayModelUpdates(updates);

					   })
		
					   .whenExists(modelObserved(modelId))
					
					   .onCancel(() -> {
						
						
					   })
					
					   .onFailure(exception2 -> {
						
						
					   })

					   .getTask();
	}

}
