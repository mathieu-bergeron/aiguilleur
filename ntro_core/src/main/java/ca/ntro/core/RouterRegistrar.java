package ca.ntro.core;

public interface RouterRegistrar {

	void registerRouter(String string, Class<? extends NtroUser> userClass, Class<? extends NtroMessage> messageClass);
	
	void registerRouter(NtroRouter<?,?> router);

}
