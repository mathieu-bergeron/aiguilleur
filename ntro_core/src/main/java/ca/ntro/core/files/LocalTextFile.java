package ca.ntro.core.files;

import ca.ntro.core.wrappers.future.Future;

public interface LocalTextFile {
	
	Future<Void> append(String value);

}
