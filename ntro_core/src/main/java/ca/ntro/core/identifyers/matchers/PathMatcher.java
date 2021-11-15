package ca.ntro.core.identifyers.matchers;

import ca.ntro.core.path.Path;

public interface PathMatcher extends Matcher<Path> {

	static PathMatcher fromSingleName(String idPattern) {
		return null;
	}

}
