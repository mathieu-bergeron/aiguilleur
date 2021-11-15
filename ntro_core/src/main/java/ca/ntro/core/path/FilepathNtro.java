package ca.ntro.core.path;

import ca.ntro.core.util.ArrayUtils;
import ca.ntro.core.validation.Validator;

public class FilepathNtro extends PathGenericNtro<Filepath, FilepathNtro> implements Filepath {
	
	@Override
	protected FilepathNtro newInstance() {
		return new FilepathNtro();
	}

	@Override
	protected String[] validNameCharacters() {
		return ArrayUtils.addString(Validator.validIdCharacters, Path.FILENAME_SEPARATOR);
	}

}
