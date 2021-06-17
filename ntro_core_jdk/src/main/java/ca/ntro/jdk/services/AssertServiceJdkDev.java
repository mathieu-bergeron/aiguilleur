package ca.ntro.jdk.services;

import ca.ntro.jj.services.AssertService;

public class AssertServiceJdkDev extends AssertService {

	@Override
	public void fail(String message) {
		throw new AssertionError(message);
	}
}
