package ca.ntro.core.path;


import ca.ntro.core.util.ArrayUtils;
import ca.ntro.core.validation.Validator;

public class FilepathNtro extends GenericPathNtro<Filepath, FilepathNtro> implements Filepath {
	
	@Override
	protected FilepathNtro newInstance() {
		return new FilepathNtro();
	}

	@Override
	protected String[] validNameCharacters() {
		return ArrayUtils.addString(Validator.validKeyCharacters, Path.FILENAME_SEPARATOR);
	}

	@Override
	public Filepath directory() {
		return parent();
	}

	@Override
	public String filename() {
		return lastName();
	}

}
