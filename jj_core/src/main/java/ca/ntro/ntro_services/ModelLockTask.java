package ca.ntro.ntro_services;

import ca.ntro.backend.BackendError;

public interface ModelLockTask<O extends Object> {

	O execute() throws BackendError;

}
