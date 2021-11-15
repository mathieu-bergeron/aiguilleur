package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.identifyers.EntityId;
import ca.ntro.core.identifyers.FilepathMatcher;

public class EntityIdMatcherNtro implements EntityIdMatcher {

	private FilepathMatcher pathMatcher;

	public EntityIdMatcherNtro(String rawPattern) {
		this.pathMatcher = FilepathMatcher.fromRawPattern(rawPattern);
	}

	public EntityIdMatcherNtro(FilepathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	@Override
	public boolean matches(EntityId id) {
		return pathMatcher.matches(id.toFilepath());
	}


}
