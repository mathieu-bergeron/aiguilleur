package ca.ntro.app.frontend;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;

public interface Frontend<VR extends ViewRegistrar<?>> {


	void registerViews(VR registrar);

	void registerControllers(ControllerRegistrar registar);

}
