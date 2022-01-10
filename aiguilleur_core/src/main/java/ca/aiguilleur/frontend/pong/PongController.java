package ca.aiguilleur.frontend.pong;

import ca.ntro.app.frontend.tasks.FrontendTaskCreator;
import ca.ntro.app.frontend.tasks.TypedFrontendTaskDescriptor;
import ca.ntro.app.frontend.tasks.FrontendTaskNull;
import ca.ntro.app.models.ModelUpdates;

import static ca.ntro.app.frontend.tasks.Factory.*;

import ca.aiguilleur.frontend.events.EvtShowGameView;
import ca.aiguilleur.models.PongModel;
import ca.aiguilleur.models.QueueModel;

public class PongController {
	
	private static TypedFrontendTaskDescriptor<?> displayGameSubTask = new FrontendTaskNull();

	public static void createTasks(FrontendTaskCreator to) {
		displayCurrentGame(to);
	}

	private static void displayCurrentGame(FrontendTaskCreator to) {
		
		to.implement(task("displayCurrentGame"))

		  .waitFor(view(GameView.class))

		  .waitFor(event(EvtShowGameView.class))

		  .thenExecuteAsync((inputs, notify) -> {

			  EvtShowGameView message = inputs.get(event(EvtShowGameView.class));
			  String gameId = message.getGameId();
				   
			  displayGameSubTask.destroy(); // XXX: removes the task from the TaskGraph
				   
			  displayGameSubTask = displayGameByModelId(notify.to(), gameId);

		   });
	}

	private static TypedFrontendTaskDescriptor<?> displayGameByModelId(FrontendTaskCreator to, String gameId) {

		return to.implement(task("displayGameByModelId"))

			     .waitFor(modelUpdates(QueueModel.class, gameId))

				 .thenExecuteAsync((inputs, notify) -> {

					 GameView     view    = inputs.get(view(GameView.class));
					 ModelUpdates updates = inputs.get(modelUpdates(PongModel.class, gameId));

					 view.displayModelUpdates(updates);
			     })

			     .getTask();
	}
}
