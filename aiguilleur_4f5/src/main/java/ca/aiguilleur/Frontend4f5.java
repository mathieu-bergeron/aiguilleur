package ca.aiguilleur;

import ca.aiguilleur.frontend.events.EvtShowGameView;
import ca.aiguilleur.frontend.events.EvtShowQueueView;
import ca.aiguilleur.frontend.pong.GameView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.frontend.root.RootController;
import ca.aiguilleur.frontend.root.RootView;
import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;
import ca.ntro.app.frontend.tasks.FrontendTaskCreator;

public class Frontend4f5 implements FrontendFx {

	@Override
	public void registerEvents(EventRegistrar registrar) {

		registrar.registerEvent(EvtShowQueueView.class);
		registrar.registerEvent(EvtShowGameView.class);

	}

	@Override
	public void registerViews(ViewRegistrarFx registrar) {

		registrar.registerStylesheet("/style.css");

		registrar.registerResources(NtroApp.locale("fr"), "/strings_fr.properties");
		registrar.registerResources(NtroApp.locale("en"), "/strings_en.properties");

		registrar.registerView(RootView.class, "/root.xml");
		registrar.registerView(QueueView.class, "/queue.xml");
		registrar.registerView(GameView.class, "/game.xml");

	}

	@Override
	public void createTasks(FrontendTaskCreator to) {

		RootController.createTasks(to);
		//MenuController.createTasks(to);
		//QueueController.createTasks(to);

	}

	@Override
	public void execute() {
		EvtShowQueueView evtShowQueueView = NtroApp.events().newEvent(EvtShowQueueView.class);
		evtShowQueueView.trigger();
	}
}
