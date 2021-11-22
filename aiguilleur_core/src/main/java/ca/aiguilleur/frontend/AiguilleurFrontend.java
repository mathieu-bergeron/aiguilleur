package ca.aiguilleur.frontend;

import ca.ntro.app.frontend.ControllerRegistrar;
import ca.ntro.app.frontend.Frontend;
import ca.ntro.app.frontend.ViewRegistrar;

public abstract class AiguilleurFrontend<VR extends ViewRegistrar<?>> implements Frontend<VR> {

	@Override
	public void registerControllers(ControllerRegistrar registar) {
		
	}

}
