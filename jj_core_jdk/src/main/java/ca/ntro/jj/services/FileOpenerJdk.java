package ca.ntro.jj.services;

import java.io.File;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.files.LocalTextFileJdk;
import ca.ntro.jj.services.FileOpener;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.values.Path;

public class FileOpenerJdk extends FileOpener {

	@Override
	public LocalTextFile openLocalTextFile(Path path) {
		
		File file = new File("_test.txt");
		
		return new LocalTextFileJdk(file);
	}

	public static Task initialisationTask() {

		return null;
	}

}
