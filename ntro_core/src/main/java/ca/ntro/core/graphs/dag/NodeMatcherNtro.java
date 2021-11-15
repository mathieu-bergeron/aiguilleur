package ca.ntro.core.graphs.dag;

import ca.ntro.core.identifyers.matchers.FilepathMatcher;

public class NodeMatcherNtro<N extends Node> implements NodeMatcher<N> {

	private FilepathMatcher pathMatcher;

	public NodeMatcherNtro(String idPattern) {
		this.pathMatcher = FilepathMatcher.fromSingleName(idPattern);
	}

	public NodeMatcherNtro(FilepathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	@Override
	public boolean matches(N node) {
		return pathMatcher.matches(node.id().toFilepath());
	}

}
