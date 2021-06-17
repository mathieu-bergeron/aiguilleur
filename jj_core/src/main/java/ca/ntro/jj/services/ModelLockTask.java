package ca.ntro.jj.services;

import ca.ntro.backend.BackendError;

public interface ModelLockTask<O extends Object> {

	O execute() throws BackendError;

}
