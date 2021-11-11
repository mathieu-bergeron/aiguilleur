package ca.ntro.jj.services;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.files.LocalTextFileNull;
import ca.ntro.jj.identifyers.Path;

public class FileOpenerNull extends FileOpener {

	@Override
	public LocalTextFile openLocalTextFile(Path path) {
		return new LocalTextFileNull();
	}
}