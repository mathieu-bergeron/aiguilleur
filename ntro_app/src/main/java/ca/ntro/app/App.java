package ca.ntro.app;

import ca.ntro.app.frontend.ViewRegistrar;
import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;

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
