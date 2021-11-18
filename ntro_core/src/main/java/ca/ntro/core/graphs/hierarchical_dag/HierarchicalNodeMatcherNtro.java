package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.NodeMatcherByValue;

public class      HierarchicalNodeMatcherNtro<NV extends NodeValue> 
       extends    NodeMatcherByValue<NV>
       implements HierarchicalNodeMatcher<NV> {

	public HierarchicalNodeMatcherNtro(NV value) {
		super(value);
	}

}
