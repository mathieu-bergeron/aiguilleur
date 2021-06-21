package ca.ntro.jj.server;

import ca.ntro.core.tasks.NtroTask;
import ca.ntro.core.tasks.NtroTaskAsync;
import ca.ntro.jj.services.LoggerNull;
import ca.ntro.jj.services.logger.Logger;

public abstract class ServerJj implements Server {

	private Logger logger;
	private ServerOptions options;

	public ServerJj(Logger logger, ServerOptions options) {
		logger.trace(this);
		
		this.logger = logger;
		this.options = options;
	}

	protected ServerOptions getOptions() {
		return options;
	}

	protected void setOptions(ServerOptions options) {
		this.options = options;
	}

	protected Logger getLogger() {
		return logger;
	}

	protected void setLogger(Logger logger) {
		this.logger = logger;
	}
}
