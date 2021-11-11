package ca.ntro.core.services;

import ca.ntro.core.files.RemoteTextFile;
import ca.ntro.core.initialization.Service;
import ca.ntro.core.values.Url;
import ca.ntro.core.wrappers.future.Future;

public abstract class FileFetcher extends Service<FileFetcher> {

	public abstract Future<RemoteTextFile> openRemoteTextFile(Url url);
}
