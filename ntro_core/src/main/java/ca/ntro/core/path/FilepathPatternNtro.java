package ca.ntro.core.path;

import ca.ntro.core.util.ArrayUtils;
import ca.ntro.core.validation.Validator;

public class FilepathPatternNtro extends GenericPathNtro<FilepathPattern, FilepathPatternNtro> implements FilepathPattern {

	@Override
	protected FilepathPatternNtro newInstance() {
		return new FilepathPatternNtro();
	}

	@Override
	protected String[] validNameCharacters() {
		String[] validNameCharacters = Validator.validIdCharacters;

		validNameCharacters = ArrayUtils.addString(validNameCharacters, Path.FILENAME_SEPARATOR);
		validNameCharacters = ArrayUtils.addString(validNameCharacters, PathPattern.NAME_WILDCARD);

		return validNameCharacters;
	}
}
