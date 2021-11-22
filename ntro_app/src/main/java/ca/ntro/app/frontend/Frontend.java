package ca.ntro.app.frontend;

import ca.ntro.tmp.ControllerRegistrar;
import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;

public interface Frontend<VR extends ViewRegistrar> {


	void registerViews(VR registrar);

	void registerControllers(ControllerRegistrar registar);

}
