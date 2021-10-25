package ca.jj.jdk.services;

import java.io.File;

import ca.jj.core.Path;
import ca.jj.jdk.files.LocalTextFileJdk;
import ca.ntro.jj.core.files.LocalTextFile;
import ca.ntro.jj.core.services.FileOpener;

public class FileOpenerJdk implements FileOpener {

	@Override
	public LocalTextFile openLocalTextFile(Path path) {
		
		File file = new File("_test.txt");
		
		return new LocalTextFileJdk(file);
	}

}
