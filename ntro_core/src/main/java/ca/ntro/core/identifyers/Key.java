package ca.ntro.core.identifyers;

import ca.ntro.core.exceptions.InvalidCharacterException;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.validation.Validator;

public final class Key {

	public static final String[] validKeyCharacters = {
			"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
			"0","1","2","3","4","5","6","7","8","9",
			".","-","_","@",":","[","]"
			};

	public static void mustBeValidKey(String key) throws InvalidCharacterException {
		Validator.mustContainOnlyValidCharacters(key, validKeyCharacters);
	}
	
	private String key;
	
	public Key(String key) {
		try {

			mustBeValidKey(key);

		} catch (InvalidCharacterException e) {
			
			Ntro.exceptionThrower().throwException(new RuntimeException("Key cannot contain character " + e));
		}
		
		this.key = key;
	}

	@Override
	public String toString() {
		return key;
	}
}
