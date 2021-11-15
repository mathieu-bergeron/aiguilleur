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
		FilepathPattern filePath = FilepathPattern.fromRawPattern(idPattern);

		PathPattern categoryPath = PathPattern.fromPath(filePath.subPath(0, filePath.nameCount()-1));
		PathPattern entityPath = PathPattern.fromFilename(filePath.lastName());
		
		this.entityPathMatcher = new PathMatcherNtro(entityPath);
		this.categoryPathMatcher = new PathMatcherNtro(categoryPath);
	}

	public StorageIdMatcherNtro(PathMatcher entityPathMatcher, PathMatcher categoryPathMatcher) {
		this.entityPathMatcher = entityPathMatcher;
		this.categoryPathMatcher = categoryPathMatcher;
	}

	@Override
	public boolean matches(StorageId id) {

		Filepath filePath = id.toFilepath();
		Path categoryPath = Path.fromPath(filePath.subPath(0, filePath.nameCount()-1));
		Path entityPath = Path.fromFilename(filePath.lastName());
		
		return entityPathMatcher.matches(entityPath) 
				&& categoryPathMatcher.matches(categoryPath);
	}
}
