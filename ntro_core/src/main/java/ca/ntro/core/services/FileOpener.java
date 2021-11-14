package ca.ntro.core.services;

import ca.ntro.core.files.LocalTextFile;
import ca.ntro.core.initialization.Service;
import ca.ntro.core.path.Path;

public abstract class FileOpener extends Service<FileOpener> {
	
	public abstract LocalTextFile openLocalTextFile(Path path);

}
