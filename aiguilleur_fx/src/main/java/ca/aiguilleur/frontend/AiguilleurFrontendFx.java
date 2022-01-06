package ca.aiguilleur.frontend;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.aiguilleur.frontend.root.RootView;
import ca.ntro.app.events.EventRegistrar;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;

public class AiguilleurFrontendFx extends AiguilleurFrontend<ViewRegistrarFx> implements FrontendFx {

	@Override
	public void registerViews(ViewRegistrarFx registrar) {

		registrar.registerView(RootView.class, "/root.xml");
		registrar.registerView(MenuView.class, "/menu.xml");
		registrar.registerView(QueueView.class, "/queue.xml");
		registrar.registerView(PongView.class, "/pong.xml");
	}

	@Override
	public void registerEvents(EventRegistrar registrar) {
		
	}

}
