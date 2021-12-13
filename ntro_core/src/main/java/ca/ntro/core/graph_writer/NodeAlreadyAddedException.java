package ca.ntro.core.graph_writer;

public class NodeAlreadyAddedException extends GraphWriterException {
	private static final long serialVersionUID = 8424792238796155480L;

	public NodeAlreadyAddedException(String message) {
		super(message);
	}

}
