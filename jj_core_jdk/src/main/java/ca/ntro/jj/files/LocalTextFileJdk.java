package ca.ntro.jj.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.wrappers.future.Future;

public class LocalTextFileJdk implements LocalTextFile {
	
	private File file;
	
	public LocalTextFileJdk(File file) {
		this.file = file;
	}

	@Override
	public Future<Void> write(String value) {
		
		Future<Void> future = null;
		
		try {

			FileWriter writer = new FileWriter(file);
			writer.write(value);
			writer.close();
			
			// Future . notifyOK
			

		} catch (IOException e) {

			e.printStackTrace();
			
			// Future.registerException(e);
		}
		
		return future;
	}
}
