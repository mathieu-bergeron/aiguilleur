package ca.ntro.jj.services;

import ca.ntro.jj.services.logger.Logger;

public class NullLogger implements Logger {

	@Override
	public void text(String text) {
	}

	@Override
	public void value(Object value) {
	}

	@Override
	public void trace(Object calledObjectOrClass) {
	}

}
