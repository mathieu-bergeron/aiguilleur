package ca.aiguilleur;

import ca.aiguilleur.frontend.menu.MenuController;
import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pages.PagesController;
import ca.aiguilleur.frontend.pages.PagesView;
import ca.aiguilleur.frontend.pong.PongController;
import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueController;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.frontend.root.AiguilleurRootController;
import ca.ntro.app.events.EventRegistrar;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.RootView;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.controllers.tasks.FrontendTasks;

public class Frontend4f5 implements FrontendFx {

	@Override
	public void registerViews(ViewRegistrarFx registrar) {

		registrar.registerView(RootView.class, "/root.xml");
		registrar.registerView(MenuView.class, "/menu.xml");
		registrar.registerView(PagesView.class, "/pages.xml");
		registrar.registerView(QueueView.class, "/queue.xml");
		registrar.registerView(PongView.class, "/pong.xml");
	}

	@Override
	public void registerEvents(EventRegistrar registrar) {
		
	}

	@Override
	public void createTasks(FrontendTasks inOrder) {

		AiguilleurRootController.createTasks(inOrder);
		MenuController.createTasks(inOrder);
		PagesController.createTasks(inOrder);
		QueueController.createTasks(inOrder);
		PongController.createTasks(inOrder);
	}
}
