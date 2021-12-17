package ca.ntro.core.services;

import java.util.Timer;
import java.util.TimerTask;

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

	@Override
	public void runAfterDelay(long milliseconds, Runnable runnable) {
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				runnable.run();
			}

		}, milliseconds);
	}

}
