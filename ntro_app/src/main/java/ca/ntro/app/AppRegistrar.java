package ca.ntro.app;

import ca.ntro.app.backend.Backend;
import ca.ntro.app.frontend.Frontend;

public interface AppRegistrar {
	
	void registerFrontend(Frontend frontend);
	void registerBackend(Backend backend);

}
