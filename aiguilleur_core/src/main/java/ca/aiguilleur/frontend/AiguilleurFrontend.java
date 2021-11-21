package ca.aiguilleur.frontend;

import ca.ntro.app.frontend.Frontend;
import ca.ntro.app.frontend.ViewRegistrar;
import ca.ntro.tmp.ControllerRegistrar;
import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;

public abstract class AiguilleurFrontend<VR extends ViewRegistrar<?>> implements Frontend<VR> {

	@Override
	public void registerModels(ModelRegistrar registrar) {
		
	}


	@Override
	public void registerMessages(MessageRegistrar registrar) {
		
	}

	@Override
	public void registerControllers(ControllerRegistrar registar) {
		
	}

}
