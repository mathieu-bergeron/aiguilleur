package ca.ntro.core.services;

import ca.ntro.core.initialization.Ntro;

public class TimeJdk implements Time {

	@Override
	public void sleep(long milliseconds) {
		try {

			Thread.sleep(milliseconds);

		} catch (InterruptedException e) {

			Ntro.exceptions().throwException(e);
		}
	}

	@Override
	public long nowMilliseconds() {
		return System.currentTimeMillis();
	}

}
