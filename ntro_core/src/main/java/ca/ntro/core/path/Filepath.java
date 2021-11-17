package ca.ntro.core.path;

public interface Filepath extends GenericPath<Filepath> {
	
	Filepath directory();
	String  filename();
	

	static Filepath fromPath(GenericPath<?> path) {
		FilepathNtro result = new FilepathNtro();
		
		result.fromGenericPath(path);

		return result;
	}

	static Filepath fromSingleName(String name) {
		FilepathNtro path = new FilepathNtro();
		
		path.addName(name);
		
		return path;
	}

	static Filepath emptyPath() {
		FilepathNtro path = new FilepathNtro();
		return path;
	}

}
