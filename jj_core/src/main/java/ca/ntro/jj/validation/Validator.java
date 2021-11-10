package ca.ntro.jj.validation;

import ca.ntro.jj.exceptions.InvalidCaracterException;

public class Validator {

	public static void mustNotContainCharacter(String value, String[] invalidCharacters) throws InvalidCaracterException {
		for(String invalidCharacter : invalidCharacters) {
			if(value.contains(invalidCharacter)) {
				throw new InvalidCaracterException(invalidCharacter);
			}
		}
	}
}
