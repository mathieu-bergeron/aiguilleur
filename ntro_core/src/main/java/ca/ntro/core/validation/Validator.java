package ca.ntro.core.validation;


import ca.ntro.core.exceptions.InvalidCharacterException;
import ca.ntro.core.util.ArrayUtils;

public class Validator {
	
	public static final String[] validIdCharacters = {
			"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
			".", "-", "_",
			};

	public static void mustNotContainCharacter(String value, String[] invalidCharacters) throws InvalidCharacterException {
		for(String invalidCharacter : invalidCharacters) {
			if(value.contains(invalidCharacter)) {
				throw new InvalidCharacterException(invalidCharacter);
			}
		}
	}

	public static void mustBeValidId(String id) throws InvalidCharacterException {
		for(int i = 0; i < id.length(); i++) {
			String currentChar = String.valueOf(id.charAt(i));
			if(!ArrayUtils.containsString(validIdCharacters, currentChar)) {
				throw new InvalidCharacterException(currentChar);
			}
		}
	}
}