package ca.ntro.core.graphs.writers;

public class NodeNotFoundException extends GraphWriterException {
	private static final long serialVersionUID = 7615012531578035868L;

	public NodeNotFoundException(String message) {
		super(message);
	}
}
