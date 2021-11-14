package ca.ntro.core.services;

import ca.ntro.core.files.LocalTextFile;
import ca.ntro.core.files.LocalTextFileNull;
import ca.ntro.core.path.Path;

public class FileOpenerNull extends FileOpener {

	@Override
	public LocalTextFile openLocalTextFile(Path path) {
		return new LocalTextFileNull();
	}
}
