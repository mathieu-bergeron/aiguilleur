package ca.ntro.jj.services;

public class LoggerNull implements Logger {

	@Override
	public void exception(Throwable e) {
	}

	@Override
	public void trace(String message) {
	}

}
