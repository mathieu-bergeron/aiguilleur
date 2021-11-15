package ca.ntro.core.identifyers;

import ca.ntro.core.identifyers.matchers.Matcher;
import ca.ntro.core.path.Filepath;

public interface FilepathMatcher extends Matcher<Filepath> {

	static FilepathMatcher fromSingleName(String idPattern) {
		return null;
	}

	static FilepathMatcher fromRawPattern(String rawPattern) {
		return null;
	}

}
