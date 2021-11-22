package ca.aiguilleur.frontend;

import ca.aiguilleur.frontend.menu.MenuController;
import ca.aiguilleur.frontend.pages.PagesController;
import ca.aiguilleur.frontend.pong.PongController;
import ca.aiguilleur.frontend.queue.QueueController;
import ca.aiguilleur.frontend.root.AiguilleurRootController;
import ca.ntro.app.frontend.ControllerRegistrar;
import ca.ntro.app.frontend.Frontend;
import ca.ntro.app.frontend.ViewRegistrar;

public abstract class AiguilleurFrontend<VR extends ViewRegistrar<?>> implements Frontend<VR> {

	@Override
	public void registerControllers(ControllerRegistrar registrar) {
		
		registrar.registerRootController(AiguilleurRootController.class);
		
		registrar.registerController(MenuController.class);
		registrar.registerController(PagesController.class);
		registrar.registerController(QueueController.class);
		registrar.registerController(PongController.class);
	}

}
