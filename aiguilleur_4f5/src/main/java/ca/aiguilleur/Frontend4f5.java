package ca.aiguilleur;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.frontend.root.RootController;
import ca.aiguilleur.frontend.root.RootView;
import ca.ntro.app.events.EventRegistrar;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;

public class Frontend4f5 implements FrontendFx {

	@Override
	public void registerEvents(EventRegistrar registrar) {
	}

	@Override
	public void registerViews(ViewRegistrarFx registrar) {

		registrar.registerView(RootView.class, "/root.xml");
		//registrar.registerView(MenuView.class, "/menu.xml");
		registrar.registerView(QueueView.class, "/queue.xml");
		registrar.registerView(PongView.class, "/pong.xml");

	}

	@Override
	public void createTasks(FrontendTaskCreator to) {

		RootController.createTasks(to);
		//MenuController.createTasks(to);
		//QueueController.createTasks(to);

	}
}
