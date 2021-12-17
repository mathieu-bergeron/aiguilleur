package ca.ntro.core.services;

public class TimeNull implements Time {

	@Override
	public void sleep(long miliseconds) {
	}

	@Override
	public long nowMilliseconds() {
		return 0;
	}

}
