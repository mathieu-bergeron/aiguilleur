package ca.ntro.core.identifyers;

import ca.ntro.core.exceptions.InvalidCharacterException;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.validation.Validator;

public final class Key {
	
	private String key;
	
	public Key(String key) {
		try {

			Validator.mustBeValidKey(key);

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
