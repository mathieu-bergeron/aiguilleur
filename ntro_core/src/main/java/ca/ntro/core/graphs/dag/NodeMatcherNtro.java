package ca.ntro.core.graphs.dag;

import ca.ntro.core.identifyers.matchers.PathMatcher;
import ca.ntro.core.identifyers.matchers.PathMatcherNtro;

public class NodeMatcherNtro<N extends Node> implements NodeMatcher<N> {

	private PathMatcher pathMatcher;

	public NodeMatcherNtro(String idPattern) {
		this.pathMatcher = new PathMatcherNtro(idPattern);
	}

	public NodeMatcherNtro(PathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	@Override
	public boolean matches(N node) {
		return pathMatcher.matches(node.id().toFilePath());
	}

}
