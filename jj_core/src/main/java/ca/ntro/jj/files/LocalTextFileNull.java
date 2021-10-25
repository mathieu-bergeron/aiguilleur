package ca.ntro.jj.files;

import ca.ntro.jj.wrappers.future.Future;
import ca.ntro.jj.wrappers.future.FutureNull;

public class LocalTextFileNull implements LocalTextFile {

	@Override
	public Future<Void> append(String value) {
		return new FutureNull<Void>();
	}
}
