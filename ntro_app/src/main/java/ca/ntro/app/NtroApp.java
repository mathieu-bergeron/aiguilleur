package ca.ntro.app;

import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;

public interface NtroApp {

	void registerModels(ModelRegistrar registrar);
	void registerMessages(MessageRegistrar registrar);
	
	void registerApp(AppRegistrar registrar);

	public static void launch(String[] args) {
		// TODO: get class from 
		
		// instantiate
		
		// call registerApp
		
	}
	
}
