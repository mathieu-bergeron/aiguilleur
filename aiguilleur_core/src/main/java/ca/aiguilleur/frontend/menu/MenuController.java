package ca.aiguilleur.frontend.menu;

import ca.aiguilleur.messages.DisplayPong;
import ca.aiguilleur.messages.DisplayQueue;
import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.TaskCreator;

public class MenuController implements Controller {

	@Override
	public void createTasks(TaskCreator creator) {
		
		creator.when().viewLoaded()
		       .execute(results -> {
		    	   
		    	   MenuView view = results.getView(MenuView.class);
		    	   
		    	   view.installDisplayQueueMessage(new DisplayQueue());
		    	   view.installDisplayPongMessage(new DisplayPong());
		       });
	}

}
