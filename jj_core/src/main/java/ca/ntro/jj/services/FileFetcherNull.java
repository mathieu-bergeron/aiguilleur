package ca.ntro.jj.services;

import ca.ntro.jj.files.RemoteTextFile;
import ca.ntro.jj.values.Url;
import ca.ntro.jj.wrappers.future.Future;
import ca.ntro.jj.wrappers.future.FutureNull;

public class FileFetcherNull implements FileFetcher {

	@Override
	public Future<RemoteTextFile> openRemoteTextFile(Url url) {
		return new FutureNull<RemoteTextFile>();
	}

}
