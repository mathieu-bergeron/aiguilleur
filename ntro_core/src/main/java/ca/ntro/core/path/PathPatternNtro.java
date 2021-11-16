package ca.ntro.core.path;

import ca.ntro.core.util.ArrayUtils;
import ca.ntro.core.validation.Validator;

public class PathPatternNtro extends GenericPathNtro<PathPattern, PathPatternNtro> implements PathPattern {

	@Override
	protected PathPatternNtro newInstance() {
		return new PathPatternNtro();
	}

	@Override
	protected String[] validNameCharacters() {
		return ArrayUtils.addString(Validator.validKeyCharacters, PathPattern.NAME_WILDCARD);
	}
}
