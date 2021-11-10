package ca.ntro.jj.exceptions;

public class InvalidCaracterException extends Exception {
	private static final long serialVersionUID = 719650085811038366L;

	private String invalidCharacter;
	
	public InvalidCaracterException(String invalidCharacter) {
		super();
		this.invalidCharacter = invalidCharacter;
	}
	
	public String invalidCharacter() {
		return invalidCharacter;
	}
}
