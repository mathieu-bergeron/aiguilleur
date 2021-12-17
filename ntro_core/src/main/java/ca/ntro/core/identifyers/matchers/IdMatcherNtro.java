package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.identifyers.IdPathNtro;

public class IdMatcherNtro implements IdMatcher {
	
	private FilepathMatcher pathMatcher;

	public IdMatcherNtro(String idPattern) {
		this.pathMatcher = FilepathMatcher.fromSingleName(idPattern);
	}

	public IdMatcherNtro(FilepathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	@Override
	public boolean matches(IdPathNtro id) {
		return pathMatcher.matches(id.toFilepath());
	}

}
