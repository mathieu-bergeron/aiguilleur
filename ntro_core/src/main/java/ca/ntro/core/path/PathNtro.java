package ca.ntro.core.path;


import ca.ntro.core.validation.Validator;

public class PathNtro extends PathGenericNtro<Path,PathNtro> implements Path {

	@Override
	protected PathNtro newInstance() {
		return new PathNtro();
	}

	@Override
	protected String[] validCharacters() {
		return Validator.validIdCharacters;
	}

}
