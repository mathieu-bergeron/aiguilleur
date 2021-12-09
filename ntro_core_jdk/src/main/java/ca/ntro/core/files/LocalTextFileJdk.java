package ca.ntro.core.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.future.FutureNtro;

public class LocalTextFileJdk implements LocalTextFile {
	
	private File file;
	
	public LocalTextFileJdk(File file) {
		this.file = file;
	}

	@Override
	public Future<Void> append(String value) {

		FutureNtro<Void> future = new FutureNtro<Void>();

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