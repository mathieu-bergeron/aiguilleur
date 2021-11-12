package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.identifyers.matchers.PathMatcher;
import ca.ntro.core.identifyers.matchers.PathMatcherNtro;

public class HierarchicalNodeMatcherNtro<HN extends HierarchicalNode> implements HierarchicalNodeMatcher<HN> {

	private PathMatcher pathMatcher;

	public HierarchicalNodeMatcherNtro(String idPattern) {
		this.pathMatcher = new PathMatcherNtro(idPattern);
	}

	public HierarchicalNodeMatcherNtro(PathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	@Override
	public boolean matches(HN node) {
		return pathMatcher.matches(node.id().toFilePath());
	}
}
