package ca.ntro.app;

import ca.ntro.app.frontend.Frontend;
import ca.ntro.app.frontend.ViewRegistrar;

public interface FrontendRegistrar<VR extends ViewRegistrar<?>> {
	
	void registerFrontend(Frontend<VR> frontend);

}
