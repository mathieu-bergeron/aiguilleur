package ca.ntro.core.identifyers;

import ca.ntro.core.exceptions.InvalidCharacterException;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.validation.Validator;

public final class Name {

	public static final String[] validNameCharacters = {
			"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
			"0","1","2","3","4","5","6","7","8","9",
			"_",
			};

	public static void mustBeValidName(String name) throws InvalidCharacterException {
		Validator.mustContainOnlyValidCharacters(name, validNameCharacters);
	}
	
	private String name;
	
	public Name(String name) {
		try {

			mustBeValidName(name);

		} catch (InvalidCharacterException e) {
			
			Ntro.exceptionThrower().throwException(new RuntimeException("Name cannot contain character " + e));
		}
		
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
