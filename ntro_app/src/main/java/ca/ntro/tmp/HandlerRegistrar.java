package ca.ntro.tmp;


public interface HandlerRegistrar {

	void registerHandler(Class<? extends NtroMessage> messageClass, Class<? extends BackendMessageHandler<?>> handlerClass);

}
