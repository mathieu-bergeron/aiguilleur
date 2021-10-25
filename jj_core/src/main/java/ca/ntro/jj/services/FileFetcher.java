package ca.ntro.jj.services;

import ca.ntro.jj.files.RemoteTextFile;
import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ClassIdJj;
import ca.ntro.jj.values.Url;
import ca.ntro.jj.wrappers.future.Future;

public interface FileFetcher {

	Future<RemoteTextFile> openRemoteTextFile(Url url);

	public static ClassId<FileFetcher> classId(){
		return new ClassIdJj<FileFetcher>(FileFetcher.class);
	}
}
