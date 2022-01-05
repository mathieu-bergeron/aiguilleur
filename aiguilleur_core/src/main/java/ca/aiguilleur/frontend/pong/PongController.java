package ca.aiguilleur.frontend.pong;

import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.FrontendTask;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;
import ca.ntro.app.frontend.controllers.tasks.TaskOutputs;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

import ca.aiguilleur.messages.MsgDisplayPong;

public class PongController implements Controller {
	
	private FrontendTask displayGameSubTask = null;

	@Override
	public void createTasks(FrontendTasks inOrder) {
		displayCurrentGame(inOrder);
	}

	private void displayCurrentGame(FrontendTasks inOrder) {
		inOrder.to("displayCurrentGame")
		       .execute((inputs, outputs) -> {

				   MsgDisplayPong message = inputs.getMessage(MsgDisplayPong.class);
				   ModelId modelId = message.getModelId();
				   
				   if(displayGameSubTask != null) {
					   displayGameSubTask.cancel(); // XXX: removes the task from the TaskGraph
				   }
					   
				   displayGameSubTask = displayGameByModelId(outputs, modelId);
		       })
		       
		       .when(messageReceived(MsgDisplayPong.class))
		       
		       .onCancel(() -> {
		    	   
		    	   
		       })
		       
		       .onFailure(exception -> {
		    	   
		    	   
		       });
	}

	private FrontendTask displayGameByModelId(TaskOutputs outputs, ModelId modelId) {

		return outputs.inOrder().to("displayGameByModelId")
							    .execute((inputs2, outputs2) -> {

								     PongView view = inputs2.getDisplayedView(PongView.class);
								     ModelUpdates updates = inputs2.getModelUpdates(modelId);
		  
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
