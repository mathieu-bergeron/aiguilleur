package ca.aiguilleur.frontend.menu;

import ca.aiguilleur.messages.DisplayPongMessage;
import ca.aiguilleur.messages.DisplayQueueMessage;
import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.TaskCreator;

import static ca.ntro.app.frontend.controllers.tasks.TaskCreator.*;

public class MenuController implements Controller {

	@Override
	public void createTasks(TaskCreator creator) {
		
		creator.when(viewDisplayed(MenuView.class))
		       .execute(results -> {
		    	   
		    	   MenuView view = results.getDisplayedView(MenuView.class);

		    	   view.installDisplayQueueMessage(new DisplayQueueMessage());
		    	   view.installDisplayPongMessage(new DisplayPongMessage());
		       });
	}
}
