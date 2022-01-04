package ca.ntro.app.backend;

import ca.ntro.app.backend.handlers.HandlerCreator;

public interface LocalBackend extends Backend {

	void registerHandlers(HandlerCreator creator);

}
