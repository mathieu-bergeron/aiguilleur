package ca.ntro.jj.services;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.initialization.Service;
import ca.ntro.jj.values.Path;

public abstract class FileOpener extends Service<FileOpener> {
	
	public abstract LocalTextFile openLocalTextFile(Path path);

}
