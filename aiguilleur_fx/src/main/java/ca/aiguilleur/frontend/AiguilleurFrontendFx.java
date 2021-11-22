package ca.aiguilleur.frontend;

import ca.aiguilleur.frontend.AiguilleurFrontend;
import ca.ntro.app.frontend.FxFrontend;
import ca.ntro.app.frontend.FxViewRegistrar;

public class AiguilleurFrontendFx extends AiguilleurFrontend<FxViewRegistrar> implements FxFrontend {

	@Override
	public void registerViews(FxViewRegistrar registrar) {
		registrar.registerRootView("/root.xml", 600,400);
	}
}
