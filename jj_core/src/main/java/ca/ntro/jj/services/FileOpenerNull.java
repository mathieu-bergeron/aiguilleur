package ca.ntro.jj.services;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.files.LocalTextFileNull;
import ca.ntro.jj.values.Path;

public class FileOpenerNull implements FileOpener {

	@Override
	public LocalTextFile openLocalTextFile(Path path) {
		return new LocalTextFileNull();
	}

}
