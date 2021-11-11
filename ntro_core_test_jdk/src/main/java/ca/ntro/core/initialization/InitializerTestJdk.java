package ca.ntro.core.initialization;

import ca.ntro.core.services.AsserterJdk;

public class InitializerTestJdk {
	
	public static void initialize() {
		Ntro.registerAsserter(new AsserterJdk());
	}

}
