package ca.ntro.core.path;

import ca.ntro.core.exceptions.InvalidCharacterException;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.validation.Validator;

public class PathName {
	
	private String name;
	
	public PathName(String name) {
		try {

			Validator.mustBeValidId(name);

		} catch (InvalidCharacterException e) {
			
			Ntro.exceptionThrower().throwException(new RuntimeException("PathName cannot contain character " + e));
		}
		
		this.name = name;
	}

	public String name() {
		return name;
	}
}
