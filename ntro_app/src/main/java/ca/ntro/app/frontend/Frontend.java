package ca.ntro.app.frontend;

import ca.ntro.tmp.ControllerRegistrar;
import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;

public interface Frontend<VR extends ViewRegistrar> {

	void registerModels(ModelRegistrar registrar);
	void registerViews(VR registrar);

	void registerMessages(MessageRegistrar registrar);
	void registerControllers(ControllerRegistrar registar);

}
