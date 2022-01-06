package ca.aiguilleur.frontend;

import ca.aiguilleur.frontend.menu.MenuController;
import ca.aiguilleur.frontend.pong.PongController;
import ca.aiguilleur.frontend.queue.QueueController;
import ca.aiguilleur.frontend.root.RootController;
import ca.ntro.app.frontend.Frontend;
import ca.ntro.app.frontend.ViewRegistrar;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;

public abstract class AiguilleurFrontend<VR extends ViewRegistrar<?>> implements Frontend<VR> {

	@Override
	public void createTasks(FrontendTaskCreator inOrder) {

		RootController.createTasks(inOrder);
		MenuController.createTasks(inOrder);
		QueueController.createTasks(inOrder);
		PongController.createTasks(inOrder);

	}

}
