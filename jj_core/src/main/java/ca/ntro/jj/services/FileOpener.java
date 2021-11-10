package ca.ntro.jj.services;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.identifyers.Path;
import ca.ntro.jj.initialization.Service;

public abstract class FileOpener extends Service<FileOpener> {
	
	public abstract LocalTextFile openLocalTextFile(Path path);

}
