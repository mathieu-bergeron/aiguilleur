package ca.ntro.jj.services;

import ca.ntro.jj.files.RemoteTextFile;
import ca.ntro.jj.initialization.Service;
import ca.ntro.jj.values.Url;
import ca.ntro.jj.wrappers.future.Future;

public abstract class FileFetcher extends Service<FileFetcher> {

	public abstract Future<RemoteTextFile> openRemoteTextFile(Url url);
}
