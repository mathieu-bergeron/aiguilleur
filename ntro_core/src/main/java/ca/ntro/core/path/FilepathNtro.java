package ca.ntro.core.path;

import ca.ntro.core.util.ArrayUtils;
import ca.ntro.core.validation.Validator;

public class FilepathNtro extends PathGenericNtro<Filepath, FilepathNtro> implements Filepath {
	
	@Override
	public void addName(String name) {
		throw new RuntimeException("TODO");
	}

	@Override
	protected FilepathNtro newInstance() {
		return new FilepathNtro();
	}

	@Override
	protected String[] validCharacters() {
		return ArrayUtils.addString(Validator.validIdCharacters, Path.FILENAME_SEPARATOR);
	}

}
