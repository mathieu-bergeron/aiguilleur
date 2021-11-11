package ca.ntro.jj.initialization;

import ca.ntro.jj.services.AsserterJdk;

public class InitializerTestJdk {
	
	public static void initialize() {
		Jj.registerAsserter(new AsserterJdk());
	}

}
