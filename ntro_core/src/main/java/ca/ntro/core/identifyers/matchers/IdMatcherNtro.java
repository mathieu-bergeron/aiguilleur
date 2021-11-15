package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.path.Path;
import ca.ntro.core.path.PathPattern;

public class IdMatcherNtro implements IdMatcher {
	
	private FilepathMatcher pathMatcher;

	public IdMatcherNtro(String idPattern) {
		this.pathMatcher = FilepathMatcher.fromSingleName(idPattern);
	}

	public IdMatcherNtro(FilepathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	@Override
	public boolean matches(Id id) {
		return pathMatcher.matches(id.toFilepath());
	}

}
