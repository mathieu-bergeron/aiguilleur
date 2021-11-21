package ca.ntro.tmp;

import ca.ntro.app.ViewRegistrar;

public interface NtroApp {
	
	void registerModels(ModelRegistrar registrar);
	void registerViews(ViewRegistrar registrar);

	void registerMessages(MessageRegistrar registrar);
	void registerControllers(ControllerRegistrar registar);

}
