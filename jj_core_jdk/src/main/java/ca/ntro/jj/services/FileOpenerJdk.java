package ca.ntro.jj.services;

import java.io.File;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.files.LocalTextFileJdk;
import ca.ntro.jj.services.FileOpener;
import ca.ntro.jj.values.Path;

public class FileOpenerJdk implements FileOpener {

	@Override
	public LocalTextFile openLocalTextFile(Path path) {
		
		File file = new File("_test.txt");
		
		return new LocalTextFileJdk(file);
	}

}
