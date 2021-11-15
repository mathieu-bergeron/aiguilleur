package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.path.Path;
import ca.ntro.core.path.PathPattern;

public class PathMatcherNtro implements PathMatcher {
	
	private PathPattern pattern;

	public PathMatcherNtro(String rawPattern) {
		this.pattern = PathPattern.fromRawPattern(rawPattern);
	}
	
	public PathMatcherNtro(PathPattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean matches(Path path) {
		return matches(path, pattern);
	}

	private boolean matches(Path path, PathPattern pattern) {
		if(pattern.isRootPath()) {
			return true;
		}
		
		if(path.isRootPath()) {
			return false;
		}
		
		if(lastNameMatches(path, pattern)) {
			PathPattern nextPattern = pattern.subPath(0, pattern.nameCount()-1);
			Path nextPath = path.subPath(0, path.nameCount()-1);
			
			return matches(nextPath, nextPattern);
		}
		
		if(pattern.lastName().equals(PathPattern.SUBPATH_WILDCARD)) {
			PathPattern nextPattern = pattern.subPath(0, pattern.nameCount()-1);
			
			if(matches(path, nextPattern)) {

				return true;

			}else {

				Path nextPath = path.subPath(0, path.nameCount()-1);

				return matches(nextPath, pattern);
			}
		}

		return false;
	}

	private boolean lastNameMatches(Path path, PathPattern pattern) {
		if(pattern.lastName().equals(path.lastName())) {
			return true;
		}
		
		if(pattern.lastName().equals(PathPattern.NAME_WILDCARD)
				&& path.nameCount() > 0) {
			return true;
		}
		
		return false;
	}
}
