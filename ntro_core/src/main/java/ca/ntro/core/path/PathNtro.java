package ca.ntro.core.path;

public class PathNtro extends PathGenericNtro<Path> implements Path {

	protected PathNtro(String[] validCharacters) {
		super(validCharacters);
	}

	protected PathNtro(Path otherPath) {
		super(otherPath);
	}

}
