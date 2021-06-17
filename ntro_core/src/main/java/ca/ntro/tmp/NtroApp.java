package ca.ntro.tmp;


public interface NtroApp {
	
	void registerModels(ModelRegistrar registrar);
	void registerMessages(MessageRegistrar registrar);
	void registerControllers(ControllerRegistrar registar);

}
