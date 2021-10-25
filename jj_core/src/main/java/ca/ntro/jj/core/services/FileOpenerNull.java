package ca.ntro.jj.core.services;

import ca.jj.core.Path;
import ca.ntro.jj.core.files.LocalTextFile;
import ca.ntro.jj.core.files.LocalTextFileNull;

public class FileOpenerNull implements FileOpener {

	@Override
	public LocalTextFile openLocalTextFile(Path path) {
		return new LocalTextFileNull();
	}

}
