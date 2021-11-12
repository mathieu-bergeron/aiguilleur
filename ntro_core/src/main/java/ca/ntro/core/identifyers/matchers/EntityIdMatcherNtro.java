package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.identifyers.EntityId;

public class EntityIdMatcherNtro implements EntityIdMatcher {

	private PathMatcher pathMatcher;

	public EntityIdMatcherNtro(String idPattern) {
		this.pathMatcher = new PathMatcherNtro(idPattern);
	}

	public EntityIdMatcherNtro(PathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	@Override
	public boolean matches(EntityId id) {
		return pathMatcher.matches(id.toFilePath());
	}


}
