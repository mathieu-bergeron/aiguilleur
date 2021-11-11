package ca.ntro.server;

import ca.ntro.core.identifyers.ClassId;

public interface ServerOptions {

	//Path getPrivatePath();
	//Path getPublicPath();
	int getPort();


	public static ClassId<ServerOptions> classId() {
		return null;
	}
}
