package ca.ntro.jj.services;

import ca.ntro.jj.initialization.Service;

public abstract class Logger extends Service<Logger> {

	public abstract void exception(Throwable e);
	public abstract void trace(String message);
}
