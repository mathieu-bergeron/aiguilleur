package ca.ntro.jj.server;

import ca.ntro.jj.values.ClassId;

public interface ServerOptions {

	//Path getPrivatePath();
	//Path getPublicPath();
	int getPort();


	public static ClassId<ServerOptions> classId() {
		return new ClassId(ServerOptions.class);
	}
}
