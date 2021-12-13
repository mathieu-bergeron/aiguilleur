package ca.ntro.core.graph_writer;

public class ClusterAlreadyAddedException extends GraphWriterException {
	private static final long serialVersionUID = -3053478091363083975L;

	public ClusterAlreadyAddedException(String message) {
		super(message);
	}

}
