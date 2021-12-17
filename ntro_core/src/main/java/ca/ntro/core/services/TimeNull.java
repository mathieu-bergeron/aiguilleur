package ca.ntro.core.services;

public class TimeNull implements Time {

	@Override
	public void sleep(long miliseconds) {
	}

	@Override
	public long nowMillis() {
		return 0;
	}

	@Override
	public void runAfterDelay(long milliseconds, Runnable runnable) {
	}

}
