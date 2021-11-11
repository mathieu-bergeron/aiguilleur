package ca.ntro.core.services;

import ca.ntro.core.files.LocalTextFile;
import ca.ntro.core.identifyers.Path;
import ca.ntro.core.initialization.Service;

public abstract class FileOpener extends Service<FileOpener> {
	
	public abstract LocalTextFile openLocalTextFile(Path path);

}
