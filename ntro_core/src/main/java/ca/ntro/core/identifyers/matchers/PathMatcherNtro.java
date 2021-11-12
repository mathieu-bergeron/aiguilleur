package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.identifyers.Path;

public class PathMatcherNtro implements PathMatcher {
	
	private final String NAME_WILDCARD = "*";
	private final String SUBPATH_WILDCARD = "**";
	
	private Path pattern;

	public PathMatcherNtro(String rawPattern) {
		this.pattern = Path.fromRawPath(rawPattern);
	}
	
	public PathMatcherNtro(Path pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean matches(Path path) {
		return matches(path, pattern);
	}

	private boolean matches(Path path, Path pattern) {
		if(pattern.isRootPath()) {
			return true;
		}
		
		if(path.isRootPath()) {
			return false;
		}
		
		if(lastNameMatches(path, pattern)) {
			Path nextPattern = pattern.subPath(0, pattern.nameCount()-1);
			Path nextPath = path.subPath(0, path.nameCount()-1);
			
			return matches(nextPath, nextPattern);
		}
		
		if(pattern.lastName().equals(SUBPATH_WILDCARD)) {
			Path nextPattern = pattern.subPath(0, pattern.nameCount()-1);
			
			if(matches(path, nextPattern)) {

				return true;

			}else {

				Path nextPath = pattern.subPath(0, path.nameCount()-1);

				return matches(nextPath, pattern);
			}
		}

		return false;
	}

	private boolean lastNameMatches(Path path, Path pattern) {
		if(pattern.lastName().equals(path.lastName())) {
			return true;
		}
		
		if(pattern.lastName().equals(NAME_WILDCARD)
				&& path.nameCount() > 0) {
			return true;
		}
		
		return false;
	}
}
