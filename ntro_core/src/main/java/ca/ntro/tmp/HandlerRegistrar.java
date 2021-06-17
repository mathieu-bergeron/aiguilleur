package ca.ntro.tmp;

import ca.ntro.backend.BackendMessageHandler;
import ca.ntro.jj.messages.NtroMessage;

public interface HandlerRegistrar {

	void registerHandler(Class<? extends NtroMessage> messageClass, Class<? extends BackendMessageHandler<?>> handlerClass);

}
