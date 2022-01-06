package ca.aiguilleur.frontend.menu;

import ca.aiguilleur.messages.MsgDisplayPong;
import ca.aiguilleur.messages.MsgDisplayQueue;
import ca.ntro.app.frontend.controllers.tasks.FrontendTaskCreator;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTaskCreator.*;

public class MenuController {

	public static void createTasks(FrontendTaskCreator creator) {

		creator.whenExists(viewInstalled(MenuView.class))
		       .execute((inputs, outputs) -> {
		    	   
		    	   MenuView view = inputs.getView(MenuView.class);

		    	   view.installDisplayQueueMessage(new MsgDisplayQueue());
		    	   view.installDisplayPongMessage(new MsgDisplayPong());
		       });
	}
}
