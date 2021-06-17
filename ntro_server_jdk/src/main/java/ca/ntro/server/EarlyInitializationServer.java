package ca.ntro.server;

import java.nio.file.Path;
import java.nio.file.Paths;

import ca.ntro.jdk.services.EarlyInitializationJdk;
import ca.ntro.jj.trace.T;
import ca.ntro.services.ConfigService;

public class EarlyInitializationServer extends EarlyInitializationJdk {

	@Override
	protected ConfigService provideConfigService() {
		T.call(this);

		/*
		String userHome = System.getProperty("user.home");

		Path configFilepath = Paths.get(userHome, ".aiguilleur", "config.json");

		AquiletourConfig config = AquiletourConfig.loadFromJson(configFilepath);

		return config;

		*/
		
		return null;
	}

}
