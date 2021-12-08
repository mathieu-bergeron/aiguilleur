package ca.ntro.core.graphs.writers;

public class EdgeAlreadyAddedException extends GraphWriterException {
	private static final long serialVersionUID = 2837460823982495445L;

	public EdgeAlreadyAddedException(String message) {
		super(message);
	}

}
