package ca.aiguilleur.frontend.menu;

import static ca.ntro.app.frontend.tasks.Factory.*;

import ca.aiguilleur.messages.MsgDisplayPong;
import ca.aiguilleur.messages.MsgDisplayQueue;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;

public class MenuController {

	public static void createTasks(FrontendTaskCreator to) {

		installMenuEvents(to);

	}

	private static void installMenuEvents(FrontendTaskCreator to) {

		to.implement(task("installMenuEvents"))
		
		  .waitFor(view(MenuView.class))
		  
		  .thenExecuteAsync((inputs, notify) -> {
			  
			  MenuView view = inputs.get(view(MenuView.class));

		      view.installDisplayQueueMessage(new MsgDisplayQueue());
		      view.installDisplayPongMessage(new MsgDisplayPong());
		  });

	}
}
