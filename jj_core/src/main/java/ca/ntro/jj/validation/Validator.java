package ca.ntro.jj.validation;

import ca.ntro.jj.exceptions.InvalidCharacterException;

public class Validator {

	public static void mustNotContainCharacter(String value, String[] invalidCharacters) throws InvalidCharacterException {
		for(String invalidCharacter : invalidCharacters) {
			if(value.contains(invalidCharacter)) {
				throw new InvalidCharacterException(invalidCharacter);
			}
		}
	}
}
