package ca.ntro.core.services;

import ca.ntro.core.initialization.Service;

public abstract class Logger extends Service<Logger> {

	public abstract void exception(Throwable e);
	public abstract void trace(String message);
}
