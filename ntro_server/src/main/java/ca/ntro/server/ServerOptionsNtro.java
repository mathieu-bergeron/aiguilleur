package ca.ntro.server;


public class ServerOptionsNtro implements ServerOptions {
	
	//private Path privatePath;
	//private Path publicPath;
	private int port;

	/*
	@Override
	public Path getPrivatePath() {
		return privatePath;
	}
	*/

	/*
	public void setPrivatePath(Path privatePath) {
		this.privatePath = privatePath;
	}
	*/

	/*
	@Override
	public Path getPublicPath() {
		return publicPath;
	}
	*/


	/*
	public void setPublicPath(Path publicPath) {
		this.publicPath = publicPath;
	}
	*/

	@Override
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
