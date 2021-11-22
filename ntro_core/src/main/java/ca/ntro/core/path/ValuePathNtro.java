package ca.ntro.core.path;

import ca.ntro.core.validation.Validator;

public class ValuePathNtro extends GenericPathNtro<ValuePath, ValuePathNtro>{

	@Override
	protected ValuePathNtro newInstance() {
		return new ValuePathNtro();
	}

	@Override
	protected String[] validNameCharacters() {
		return Validator.validKeyCharacters;
	}

}
