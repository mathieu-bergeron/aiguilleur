package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.wrappers.result.Result;

public interface HierarchicalGraph<NV extends NodeValue, 
                                   EV extends EdgeValue,
                                   N extends HierarchicalNode<NV>,
                                   E extends Edge<EV>> extends GenericGraph<NV,EV,N,E> {


}
