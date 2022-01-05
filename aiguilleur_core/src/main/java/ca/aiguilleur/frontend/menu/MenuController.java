package ca.aiguilleur.frontend.menu;

import ca.aiguilleur.messages.MsgDisplayPong;
import ca.aiguilleur.messages.MsgDisplayQueue;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

public class MenuController {

	public static void createTasks(FrontendTasks creator) {

		creator.whenExists(viewInstalled(MenuView.class))
		       .execute((inputs, outputs) -> {
		    	   
		    	   MenuView view = inputs.getView(MenuView.class);

		    	   view.installDisplayQueueMessage(new MsgDisplayQueue());
		    	   view.installDisplayPongMessage(new MsgDisplayPong());
		       });
	}
}
