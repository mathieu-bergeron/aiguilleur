package ca.ntro.jj.core.services;

import ca.jj.core.Path;
import ca.ntro.jj.core.files.LocalTextFile;
import ca.ntro.jj.core.identifyers.ClassId;
import ca.ntro.jj.core.identifyers.ClassIdJj;

public interface FileOpener {
	
	LocalTextFile openLocalTextFile(Path path);

	public static ClassId<FileOpener> classId(){
		return new ClassIdJj<FileOpener>(FileOpener.class);
	}
}
