package ca.ntro.jdk.services;

import ca.ntro.jdk.regex.RegExJdk;
import ca.ntro.jj.introspection.Introspector;
import ca.ntro.jj.regex.RegEx;
import ca.ntro.jj.services.AppCloser;
import ca.ntro.jj.services.CollectionsService;
import ca.ntro.jj.services.ConfigService;
import ca.ntro.jj.services.EarlyInitialization;
import ca.ntro.jj.services.Logger;
import ca.ntro.jj.trace.T;
import ca.ntro.jj.trace.__T;

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
