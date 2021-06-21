package ca.ntro.jj.services;

import ca.ntro.jj.services.logger.Logger;

public abstract class LoggerAbstract implements Logger {

	@Override
	public void text(String text) {
		System.out.println(text);
	}

	public abstract void value(Object value);

	public abstract void trace(Object calledObjectOrClass);
}
