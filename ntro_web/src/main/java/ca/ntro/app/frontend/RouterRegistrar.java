package ca.ntro.app.frontend;

import ca.ntro.app.NtroUser;
import ca.ntro.app.messages.Message;

public interface RouterRegistrar {

	void registerRouter(String string, Class<? extends NtroUser> userClass, Class<? extends Message> messageClass);
	
	void registerRouter(NtroRouter<?,?> router);

}
