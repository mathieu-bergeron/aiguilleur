package ca.ntro.tmp;

import ca.ntro.app.messages.NtroMessage;

public interface HandlerRegistrar {

	void registerHandler(Class<? extends NtroMessage> messageClass, Class<? extends BackendMessageHandler<?>> handlerClass);

}
