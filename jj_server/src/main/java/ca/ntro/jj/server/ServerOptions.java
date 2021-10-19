package ca.ntro.jj.server;

import ca.ntro.jj.common.identifyiers.ClassId;
import ca.ntro.jj.common.identifyiers.ClassIdJj;

public interface ServerOptions {

	//Path getPrivatePath();
	//Path getPublicPath();
	int getPort();


	public static ClassId<ServerOptions> classId() {
		return new ClassIdJj<>(ServerOptions.class);
	}
}
