package ca.ntro.core.services;

import java.io.File;

import ca.ntro.core.files.LocalTextFile;
import ca.ntro.core.files.LocalTextFileJdk;
import ca.ntro.core.path.Path;
import ca.ntro.core.services.FileOpener;
import ca.ntro.core.tasks.base.Task;

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
