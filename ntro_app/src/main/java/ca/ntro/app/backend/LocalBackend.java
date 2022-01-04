package ca.ntro.app.backend;

import ca.ntro.app.backend.handlers.BackendTasks;

public interface LocalBackend extends Backend {

	void createTasks(BackendTasks creator);

}
