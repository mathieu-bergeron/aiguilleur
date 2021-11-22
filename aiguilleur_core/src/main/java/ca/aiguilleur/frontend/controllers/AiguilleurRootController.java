package ca.aiguilleur.frontend.controllers;

import ca.ntro.app.frontend.RootController;
import ca.ntro.app.frontend.controllers.TaskRegistrar;
import ca.ntro.core.tasks.base.Task;

public class AiguilleurRootController implements RootController {

	@Override
	public void registerTasks(TaskMap basicTasks, TaskRegistrar registrar) {
		
		Task rootViewTask = basicTasks.getTask("loadRootView");
		
		Task newTask = new Task();
		
		newTask.addPreviousTask(rootViewTask);
		
		registrar.registerTask(newTask);

	}

}
