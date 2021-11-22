package ca.ntro.app.backend;

public interface LocalBackend extends Backend {

	void registerMessageHandlers(MessageHandlerRegistrar registrar);

}
