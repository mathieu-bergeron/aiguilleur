package ca.aiguilleur.frontend.queue;

import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.TaskCreator;

public class QueueController implements Controller {

	@Override
	public void createTasks(TaskCreator creator) {
		
		Task display = creator.when().viewLoaded()
		                      .and().modelUpdated(QueueModel.id())
		                      .execute(new QueueDisplayTask());
		
	}

}
