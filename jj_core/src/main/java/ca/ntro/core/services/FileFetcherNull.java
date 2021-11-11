package ca.ntro.core.services;

import ca.ntro.core.files.RemoteTextFile;
import ca.ntro.core.values.Url;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.future.FutureNull;

public class FileFetcherNull extends FileFetcher {

	@Override
	public Future<RemoteTextFile> openRemoteTextFile(Url url) {
		return new FutureNull<RemoteTextFile>();
	}
}
