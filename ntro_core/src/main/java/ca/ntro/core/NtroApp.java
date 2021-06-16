package ca.ntro.core;


public interface NtroApp {
	
	void registerModels(ModelRegistrar registrar);
	void registerMessages(MessageRegistrar registrar);
	void registerControllers(ControllerRegistrar registar);

}
