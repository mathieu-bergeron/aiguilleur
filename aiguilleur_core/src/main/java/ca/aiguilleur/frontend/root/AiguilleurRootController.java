package ca.aiguilleur.frontend.root;

import javax.annotation.meta.When;

import ca.ntro.app.frontend.RootController;
import ca.ntro.app.frontend.controllers.TaskCreator;
import ca.ntro.core.tasks.base.Task;

public class AiguilleurRootController implements RootController {

	@Override
	public void createTasks(TaskCreator creator) {
		
		Task messageReceived = creator.when().messageReceived(MyMessage.class);

		Task modelUpdated = creator.when().modelUpdated(MyMessage.class);
		
		creator.when().finished(messageReceived)
		       .or().finished(modelUpdated)
		       .execute(result -> {
		    	   
		    	   
		       });
		
		Task myTask = creator.when().mainViewLoaded()
				             .and().subViewLoaded(subViewId)
		                     .and().messageReceived(MyMessage.class)
		                     .and().modelUpdated(modelId)
		                     .execute(results -> {
		                    	   
		                    	   View mainView = results.getView(MainView.class);
		                    	   View subView = results.getView(subViewId);
		                    	   Message message = results.getMessage(MyMessage.class);
		                    	   ModelUpdates modelUpdates = results.getModelUpdates(modelId);
		                    	   
		                    	   Object general = results.get(MyClass.class, objectId);
		                    	   
		                    	   for(ModelUpdate modelUpdate : modelUpdates) {

		                    		   mainView.displayUpdates(modelUpdates);
		                    	   }
		                     });

		creator.when().finished(myTask)
		       .execute(results -> {
		    	   
		    	   System.out.println("asdf");
			   });
	}

}
