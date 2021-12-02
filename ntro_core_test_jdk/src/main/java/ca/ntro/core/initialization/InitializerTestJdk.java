package ca.ntro.core.initialization;

import ca.ntro.core.services.AsserterJdk;
import ca.ntro.core.services.CollectionsJdk;
import ca.ntro.core.services.GraphWriterJdk;
import ca.ntro.core.services.ReflectionServiceJdk;

public class InitializerTestJdk {
	
	public static void initialize() {
		Ntro.registerAsserter(new AsserterJdk());
		Ntro.registerReflectionService(new ReflectionServiceJdk());
		Ntro.registerGraphWriter(GraphWriterJdk.class);
		Ntro.registerCollections(new CollectionsJdk());
	}

}
