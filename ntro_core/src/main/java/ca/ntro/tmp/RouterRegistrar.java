package ca.ntro.tmp;

import ca.ntro.messages.NtroMessage;

public interface RouterRegistrar {

	void registerRouter(String string, Class<? extends NtroUser> userClass, Class<? extends NtroMessage> messageClass);
	
	void registerRouter(NtroRouter<?,?> router);

}
