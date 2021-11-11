package ca.ntro.core.exceptions;

public class InvalidCharacterException extends Exception {
	private static final long serialVersionUID = 719650085811038366L;

	private String invalidCharacter;
	
	public InvalidCharacterException(String invalidCharacter) {
		super();
		this.invalidCharacter = invalidCharacter;
	}
	
	public String invalidCharacter() {
		return invalidCharacter;
	}
}
