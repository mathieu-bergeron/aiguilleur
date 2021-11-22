package ca.ntro.app.backend;

import ca.ntro.app.messages.Message;

public interface HandlerRegistrar {

	void registerHandler(Class<? extends Message> messageClass, Class<? extends BackendMessageHandler<?>> handlerClass);

}
