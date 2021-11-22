package ca.aiguilleur.frontend;

import ca.aiguilleur.frontend.controllers.AiguilleurRootController;
import ca.aiguilleur.frontend.controllers.MenuController;
import ca.aiguilleur.frontend.controllers.PageController;
import ca.ntro.app.frontend.ControllerRegistrar;
import ca.ntro.app.frontend.Frontend;
import ca.ntro.app.frontend.ViewRegistrar;

public abstract class AiguilleurFrontend<VR extends ViewRegistrar<?>> implements Frontend<VR> {

	@Override
	public void registerControllers(ControllerRegistrar registrar) {
		
		registrar.registerRootController(AiguilleurRootController.class);
		
		registrar.registerSubController(MenuController.class);
		registrar.registerSubController(PageController.class);
	}

}
