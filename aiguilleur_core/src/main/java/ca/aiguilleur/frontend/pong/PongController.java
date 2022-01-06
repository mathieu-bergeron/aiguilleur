package ca.aiguilleur.frontend.pong;

import ca.ntro.app.frontend.controllers.tasks.FrontendTaskDescriptor;
import ca.ntro.app.frontend.controllers.tasks.FrontendTaskNull;
import ca.ntro.app.frontend.controllers.tasks.FrontendTaskCreator;
import ca.ntro.app.models.ModelUpdates;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTaskCreator.*;

import ca.aiguilleur.messages.MsgDisplayPong;
import ca.aiguilleur.models.PongModel;
import ca.aiguilleur.models.QueueModel;

public class PongController {
	
	private static FrontendTaskDescriptor<?> displayGameSubTask = new FrontendTaskNull();

	public static void createTasks(FrontendTaskCreator to) {
		displayCurrentGame(to);
	}

	private static void displayCurrentGame(FrontendTaskCreator to) {
		
		to.implement(task("displayCurrentGame"))

		  .waitFor(view(PongView.class))

		  .waitFor(message(MsgDisplayPong.class))

		  .thenExecute((inputs, notify) -> {

			  MsgDisplayPong message = inputs.get(message(MsgDisplayPong.class));
			  String gameId = message.getGameId();
				   
			  displayGameSubTask.destroy(); // XXX: removes the task from the TaskGraph
				   
			  displayGameSubTask = displayGameByModelId(notify.to(), gameId);

		   });
	}

	private static FrontendTaskDescriptor<?> displayGameByModelId(FrontendTaskCreator to, String gameId) {

		return to.implement(task("displayGameByModelId"))

			     .waitFor(modelUpdates(QueueModel.class, gameId))

				 .thenExecute((inputs, notify) -> {

					 PongView     view    = inputs.get(view(PongView.class));
					 ModelUpdates updates = inputs.get(modelUpdates(PongModel.class, gameId));

					 view.displayModelUpdates(updates);
			     })

			     .getTask();
	}
}
