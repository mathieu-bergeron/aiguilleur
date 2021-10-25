package ca.ntro.jj.services;

import ca.ntro.jj.files.LocalTextFile;
import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ClassIdJj;
import ca.ntro.jj.values.Path;

public interface FileOpener {
	
	LocalTextFile openLocalTextFile(Path path);

	public static ClassId<FileOpener> classId(){
		return new ClassIdJj<FileOpener>(FileOpener.class);
	}
}
