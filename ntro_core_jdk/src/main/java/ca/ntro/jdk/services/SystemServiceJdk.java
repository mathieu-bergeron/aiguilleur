package ca.ntro.jdk.services;

import ca.ntro.jj.services.SystemService;
import ca.ntro.jj.trace.T;

public class SystemServiceJdk extends SystemService {

	@Override
	public String lineSeparator() {
		T.call(this);

		return System.lineSeparator();
	}
}
