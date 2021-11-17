package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generic_graph.NodeMatcherNtro;
import ca.ntro.core.graphs.generic_graph.NodeValue;

public class      HierarchicalNodeMatcherNtro<NV extends NodeValue> 
       extends    NodeMatcherNtro<NV>
       implements HierarchicalNodeMatcher<NV> {

	public HierarchicalNodeMatcherNtro(NV value) {
		super(value);
	}

}
