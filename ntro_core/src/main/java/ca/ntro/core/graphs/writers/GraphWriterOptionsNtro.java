package ca.ntro.core.graphs.writers;

public class GraphWriterOptionsNtro implements GraphWriterOptions {
	
	private boolean directed;

	public boolean getDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}

	@Override
	public boolean isDirected() {
		return getDirected();
	}

}
