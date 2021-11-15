package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.identifyers.StorageId;
import ca.ntro.core.path.Filepath;
import ca.ntro.core.path.FilepathPattern;
import ca.ntro.core.path.Path;
import ca.ntro.core.path.PathPattern;

public class StorageIdMatcherNtro implements StorageIdMatcher {

	private PathMatcher entityPathMatcher;
	private PathMatcher categoryPathMatcher;
	
	public StorageIdMatcherNtro(String idPattern) {
		FilepathPattern filepathPattern = FilepathPattern.fromRawPattern(idPattern);

		PathPattern categoryPath = PathPattern.fromPath(filepathPattern.directoryPattern());
		PathPattern entityPath = PathPattern.fromFilenamePattern(filepathPattern.filenamePattern());
		
		this.entityPathMatcher = new PathMatcherNtro(entityPath);
		this.categoryPathMatcher = new PathMatcherNtro(categoryPath);
	}

	public StorageIdMatcherNtro(PathMatcher entityPathMatcher, PathMatcher categoryPathMatcher) {
		this.entityPathMatcher = entityPathMatcher;
		this.categoryPathMatcher = categoryPathMatcher;
	}

	@Override
	public boolean matches(StorageId id) {

		Filepath filepath = id.toFilepath();
		Path categoryPath = Path.fromPath(filepath.directory());
		Path entityPath = Path.fromFilename(filepath.filename());
		
		return entityPathMatcher.matches(entityPath) 
				&& categoryPathMatcher.matches(categoryPath);
	}
}
