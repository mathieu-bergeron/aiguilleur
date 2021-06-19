package ca.ntro.jdk.services;

import ca.ntro.core.introspection.Introspector;
import ca.ntro.core.regex.RegEx;
import ca.ntro.core.system.trace.T;
import ca.ntro.core.system.trace.__T;
import ca.ntro.jdk.regex.RegExJdk;
import ca.ntro.jj.services.CollectionsService;
import ca.ntro.jj.services.Logger;
import ca.ntro.ntro_services.AppCloser;
import ca.ntro.ntro_services.ConfigService;
import ca.ntro.ntro_services.EarlyInitialization;

public class EarlyInitializationJdk extends EarlyInitialization {

	@Override
	protected AppCloser provideAppCloser() {
		__T.call(this, "provideAppCloser");

		return new AppCloserJdk();
	}

	@Override
	protected RegEx provideRegEx() {
		__T.call(this, "provideRegEx");

		return new RegExJdk();
	}

	@Override
	protected Introspector provideIntrospector() {
		T.call(this);
		
		return new IntrospectorJdk();
	}

	@Override
	protected Logger provideLogger() {
		__T.call(InitializationTaskJdk.class, "provideLogger");

		return new LoggerJdk();
	}

	@Override
	protected CollectionsService provideCollectionsService() {
		return new CollectionsServiceJdk();
	}

	@Override
	protected ConfigService provideConfigService() {
		return new ConfigService();
	}
}
