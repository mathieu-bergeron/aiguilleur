package ca.ntro.core.path;

import ca.ntro.core.util.ArrayUtils;
import ca.ntro.core.validation.Validator;

public class PathPatternNtro extends PathGenericNtro<PathPattern, PathPatternNtro> implements PathPattern {

	@Override
	protected PathPatternNtro newInstance() {
		return new PathPatternNtro();
	}

	@Override
	protected String[] validCharacters() {
		return ArrayUtils.addString(Validator.validIdCharacters, PathPattern.NAME_WILDCARD);
	}
}
