package ca.aiguilleur.backend;

import ca.ntro.app.backend.ServerDescription;

public class AiguilleurServerDescription implements ServerDescription {

	public static final int PORT = 8181;
	public static final String SERVER_NAME = "localhost";
	public static final boolean USE_SSL = false;

	@Override
	public int port() {
		return PORT;
	}

	@Override
	public String serverName() {
		return SERVER_NAME;
	}

	@Override
	public boolean useSsl() {
		return USE_SSL;
	}

}
