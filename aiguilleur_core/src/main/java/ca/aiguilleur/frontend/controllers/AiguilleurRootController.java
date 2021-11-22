package ca.aiguilleur.frontend.controllers;

import javax.annotation.meta.When;

import ca.ntro.app.frontend.RootController;
import ca.ntro.app.frontend.controllers.TaskCreator;
import ca.ntro.core.tasks.base.Task;

public class AiguilleurRootController implements RootController {

	@Override
	public void createTasks(TaskCreator newTask) {
		
		Task myTask = newTask.when().viewLoaded()
				             .and().subViewLoaded(subViewId)
		                     .and().messageReceived(MyMessage.class)
		                     .and().modelUpdated(modelId)
		                     .execute(results -> {
		                    	   
		                    	   View mainView = results.getView(MainView.class);
		                    	   Message message = results.getMessage(MyMessage.class);
		                    	   ModelUpdates modelUpdates = results.getModelUpdates(modelId);
		                    	   
		                    	   Object general = results.get(MyClass.class, objectId);
		                    	   
		                     });

		newTask.when().finished(myTask)
		       .execute(results -> {
		    	   
		    	   System.out.println("asdf");
			   });
	}

}
