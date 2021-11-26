package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalGraph;
import ca.ntro.core.graphs.hierarchical_graph.HierarchicalNode;

public interface HierarchicalDag<NV extends NodeValue, 
                                 EV extends EdgeValue,
                                 N extends HierarchicalNode<NV>,
                                 E extends Edge<EV>> extends HierarchicalGraph<NV,EV,N,E> {


}
