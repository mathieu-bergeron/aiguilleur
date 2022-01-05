package ca.ntro.app;

import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.events.EventRegistrar;
import ca.ntro.app.frontend.FrontendRegistrar;
import ca.ntro.app.frontend.ViewRegistrar;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;

public interface App<VR extends ViewRegistrar<?>> {

	void registerModels(ModelRegistrar registrar);
	void registerMessages(MessageRegistrar registrar);
	
	void registerFrontend(FrontendRegistrar<VR> registrar);
	void registerBackend(BackendRegistrar registrar);

	public static void launch(String[] args) {
		// TODO: get class from 
		
		// instantiate
		
		// call the registers
		
		// start
	}
	
}
