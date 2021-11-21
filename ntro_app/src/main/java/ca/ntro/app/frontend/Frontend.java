package ca.ntro.app.frontend;

import ca.ntro.tmp.ControllerRegistrar;
import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;

public interface Frontend {

	void registerModels(ModelRegistrar registrar);
	void registerViews(ViewRegistrar registrar);

	void registerMessages(MessageRegistrar registrar);
	void registerControllers(ControllerRegistrar registar);

}
