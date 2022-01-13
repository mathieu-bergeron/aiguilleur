package ca.aiguilleur.frontend;

import ca.aiguilleur.frontend.pong.GameView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.frontend.root.RootView;
import ca.ntro.app.NtroApp;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.frontend.events.EventRegistrar;

public class AiguilleurFrontendFx extends AiguilleurFrontend<ViewRegistrarFx> implements FrontendFx {

	@Override
	public void registerViews(ViewRegistrarFx registrar) {

		registrar.registerStylesheet("/style.css");

		registrar.registerResources(NtroApp.locale("fr"), "/strings_fr.properties");
		registrar.registerResources(NtroApp.locale("en"), "/strings_en.properties");

		registrar.registerView(RootView.class, "/root.xml");
		registrar.registerView(QueueView.class, "/queue.xml");
		registrar.registerView(GameView.class, "/pong.xml");

	}

	@Override
	public void registerEvents(EventRegistrar registrar) {
		
	}

	@Override
	public void execute() {
		
	}

}
