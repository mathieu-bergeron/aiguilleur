package ca.aiguilleur.frontend;

import ca.aiguilleur.frontend.menu.MenuView;
import ca.aiguilleur.frontend.pages.PagesView;
import ca.aiguilleur.frontend.pong.PongView;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;

public class AiguilleurFrontendFx extends AiguilleurFrontend<ViewRegistrarFx> implements FrontendFx {

	@Override
	public void registerViews(ViewRegistrarFx registrar) {

		registrar.registerRootView("/root.xml", 600,400);

		registrar.registerView(MenuView.class, "/menu.xml");
		registrar.registerView(PagesView.class, "/pages.xml");
		registrar.registerView(QueueView.class, "/queue.xml");
		registrar.registerView(PongView.class, "/pong.xml");
	}
}
