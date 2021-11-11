package ca.ntro.core.files;

import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.future.FutureNull;

public class LocalTextFileNull implements LocalTextFile {

	@Override
	public Future<Void> append(String value) {
		return new FutureNull<Void>();
	}
}
