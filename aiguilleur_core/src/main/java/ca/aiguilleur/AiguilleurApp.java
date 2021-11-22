package ca.aiguilleur;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.app.App;
import ca.ntro.app.frontend.ViewRegistrar;

public abstract class AiguilleurApp<VR extends ViewRegistrar<?>> implements App<VR> {

	@Override
	public void registerModels(ModelRegistrar registrar) {
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
	}

}
