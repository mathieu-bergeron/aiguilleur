package ca.ntro.core.path;

import ca.ntro.core.validation.Validator;

public class EdgeWalkNtro extends GenericPathNtro<EdgeWalk, EdgeWalkNtro> {

	@Override
	protected EdgeWalkNtro newInstance() {
		return new EdgeWalkNtro();
	}

	@Override
	protected String[] validNameCharacters() {
		return Validator.validKeyCharacters;
	}

}
