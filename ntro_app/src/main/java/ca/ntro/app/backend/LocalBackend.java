package ca.ntro.app.backend;

import ca.ntro.app.backend.handlers.BackendTaskCreator;

public interface LocalBackend extends Backend {

	void createTasks(BackendTaskCreator creator);

	void execute();

}
