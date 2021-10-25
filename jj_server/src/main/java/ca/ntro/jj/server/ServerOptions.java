package ca.ntro.jj.server;

import ca.ntro.jj.core.identifyers.ClassId;
import ca.ntro.jj.core.identifyers.ClassIdJj;

public interface ServerOptions {

	//Path getPrivatePath();
	//Path getPublicPath();
	int getPort();


	public static ClassId<ServerOptions> classId() {
		return new ClassIdJj<>(ServerOptions.class);
	}
}
