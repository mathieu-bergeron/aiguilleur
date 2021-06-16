package ca.ntro.core;



public interface HandlerRegistrar {

	void registerHandler(Class<? extends NtroMessage> messageClass, Class<? extends BackendMessageHandler<?>> handlerClass);

}
