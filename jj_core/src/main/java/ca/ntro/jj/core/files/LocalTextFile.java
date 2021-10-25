package ca.ntro.jj.core.files;

import ca.ntro.jj.wrappers.future.Future;

public interface LocalTextFile {
	
	Future<Void> write(String value);

}
