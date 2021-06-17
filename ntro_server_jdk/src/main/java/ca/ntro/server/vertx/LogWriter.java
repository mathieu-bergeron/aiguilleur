package ca.ntro.server.vertx;

import ca.ntro.backend.BackendError;
import ca.ntro.jj.services.ModelStoreSync;

public interface LogWriter {

	String write(ModelStoreSync modelStore, StringBuilder builder) throws BackendError;

}
