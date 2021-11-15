package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.identifyers.matchers.FilepathMatcher;

public class HierarchicalNodeMatcherNtro<HN extends HierarchicalNode> implements HierarchicalNodeMatcher<HN> {

	private FilepathMatcher pathMatcher;

	public HierarchicalNodeMatcherNtro(String rawPattern) {
		this.pathMatcher = FilepathMatcher.fromRawPattern(rawPattern);
	}

	public HierarchicalNodeMatcherNtro(FilepathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	@Override
	public boolean matches(HN node) {
		return pathMatcher.matches(node.id().toFilepath());
	}
}
