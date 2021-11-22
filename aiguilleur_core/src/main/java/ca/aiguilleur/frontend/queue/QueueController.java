package ca.aiguilleur.frontend.queue;

import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.frontend.Controller;
import ca.ntro.app.frontend.controllers.tasks.TaskCreator;
import ca.ntro.core.graphs.task_graph.Task;

public class QueueController implements Controller {

	@Override
	public void createTasks(TaskCreator creator) {
		
		Task display = creator.when().viewLoaded()
		                      .and().modelObserved(QueueModel.id())
		                      .execute(new QueueDisplayTask());
		
	}

}
