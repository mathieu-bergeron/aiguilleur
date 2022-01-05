package ca.aiguilleur.frontend;

import ca.aiguilleur.frontend.menu.MenuController;
import ca.aiguilleur.frontend.pages.PagesController;
import ca.aiguilleur.frontend.pong.PongController;
import ca.aiguilleur.frontend.queue.QueueController;
import ca.aiguilleur.frontend.root.AiguilleurRootController;
import ca.ntro.app.frontend.Frontend;
import ca.ntro.app.frontend.ViewRegistrar;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

public abstract class AiguilleurFrontend<VR extends ViewRegistrar<?>> implements Frontend<VR> {

	@Override
	public void createTasks(FrontendTasks inOrder) {
		new AiguilleurRootController().createTasks(inOrder);
		new MenuController().createTasks(inOrder);
		new PagesController().createTasks(inOrder);
		new QueueController().createTasks(inOrder);
		new PongController().createTasks(inOrder);
	}

}
