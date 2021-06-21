package ca.ntro.jj.server;

import ca.ntro.core.Path;
import ca.ntro.core.json.JsonSerializable;

public class ServerOptions implements JsonSerializable {
	
	private Path privatePath;
	private Path publicPath;
	private int port;

	public Path getPrivatePath() {
		return privatePath;
	}

	public void setPrivatePath(Path privatePath) {
		this.privatePath = privatePath;
	}

	public Path getPublicPath() {
		return publicPath;
	}

	public void setPublicPath(Path publicPath) {
		this.publicPath = publicPath;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
