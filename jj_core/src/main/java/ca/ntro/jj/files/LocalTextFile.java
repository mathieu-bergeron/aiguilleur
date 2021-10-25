package ca.ntro.jj.files;

import ca.ntro.jj.wrappers.future.Future;

public interface LocalTextFile {
	
	Future<Void> write(String value);

}
