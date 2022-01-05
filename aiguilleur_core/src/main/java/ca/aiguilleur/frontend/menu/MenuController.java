package ca.aiguilleur.frontend.menu;

import ca.aiguilleur.messages.MsgDisplayPong;
import ca.aiguilleur.messages.MsgDisplayQueue;
import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

import static ca.ntro.app.frontend.controllers.tasks.FrontendTasks.*;

public class MenuController implements Controller {

	@Override
	public void createTasks(FrontendTasks creator) {

		creator.when(viewDisplayed(MenuView.class))
		       .execute((inputs, outputs) -> {
		    	   
		    	   MenuView view = inputs.getDisplayedView(MenuView.class);

		    	   view.installDisplayQueueMessage(new MsgDisplayQueue());
		    	   view.installDisplayPongMessage(new MsgDisplayPong());
		       });
	}
}
