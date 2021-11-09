package ca.ntro.jj.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.wrappers.future.Future;
import ca.ntro.jj.wrappers.future.FutureJj;

public class LocalTextFileJdk implements LocalTextFile {
	
	private File file;
	
	public LocalTextFileJdk(File file) {
		this.file = file;
	}

	@Override
	public Future<Void> append(String value) {

		FutureJj<Void> future = new FutureJj<Void>();

		try {

			FileWriter writer = new FileWriter(file);
			writer.write(value);
			writer.close();
			
			future.registerValue(null);

		} catch (IOException e) {

			future.registerException(e);
		}
		
		return future;
	}
}