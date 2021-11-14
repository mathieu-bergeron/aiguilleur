package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.identifyers.Path;
import ca.ntro.core.identifyers.PathPattern;

public class IdMatcherNtro implements IdMatcher {
	
	private PathMatcher pathMatcher;

	
	public IdMatcherNtro(String idPattern) {
		this.pathMatcher = new PathMatcherNtro(PathPattern.fromSingleName(idPattern));
	}

	public IdMatcherNtro(PathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	@Override
	public boolean matches(Id id) {
		return pathMatcher.matches(id.toFilePath());
	}

}
