package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.exceptions.InvalidCharacterException;
import ca.ntro.core.identifyers.StorageId;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Filepath;
import ca.ntro.core.path.FilepathPattern;
import ca.ntro.core.path.Path;
import ca.ntro.core.path.PathPattern;
import ca.ntro.core.wrappers.exception_catcher.ExceptionCatcher;
import ca.ntro.core.wrappers.result.Result;

public class StorageIdMatcherNtro implements StorageIdMatcher {

	private PathMatcher entityPathMatcher;
	private PathMatcher categoryPathMatcher;
	
	public StorageIdMatcherNtro(String idPattern) {
		FilepathPattern filepathPattern = FilepathPattern.fromRawPattern(idPattern);
		
		PathPattern categoryPathPattern = extractCategoryPathPattern(filepathPattern);

		PathPattern entityPath = PathPattern.fromFilenamePattern(filepathPattern.filenamePattern());
		
		this.entityPathMatcher = new PathMatcherNtro(entityPath);
		this.categoryPathMatcher = new PathMatcherNtro(categoryPathPattern);
	}

	private PathPattern extractCategoryPathPattern(FilepathPattern filepathPattern) {
		PathPattern categoryPath = null;

		Result<PathPattern> categoryPathResult = ExceptionCatcher.executeBlocking(() -> {
			return PathPattern.fromPathPattern(filepathPattern.directoryPattern());
		});
		
		if(categoryPathResult.hasException()) {
			
			if(categoryPathResult.exception() instanceof InvalidCharacterException) {
				InvalidCharacterException e = (InvalidCharacterException) categoryPathResult.exception();
				
				Ntro.exceptionThrower().throwException(new RuntimeException("idPattern must not contain " + e.invalidCharacter()));
				
			}else {

				Ntro.exceptionThrower().throwException(categoryPathResult.exception());

			}
			
			categoryPath = PathPattern.newInstance();

		}else {
			
			categoryPath = categoryPathResult.value();
		}

		return categoryPath;
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
