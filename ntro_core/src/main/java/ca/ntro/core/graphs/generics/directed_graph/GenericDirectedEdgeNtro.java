package ca.ntro.core.graphs.generics.directed_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.EdgeTypeNtro;
import ca.ntro.core.graphs.generics.graph.GenericEdgeNtro;

public class      GenericDirectedEdgeNtro<N extends GenericDirectedNode<N,E,SO>, 
                                          E extends GenericDirectedEdge<N,E,SO>,
                                          SO extends DirectedGraphSearchOptions>

       extends    GenericEdgeNtro<N,E,SO>

       implements GenericDirectedEdge<N,E,SO> {

	public GenericDirectedEdgeNtro() {
	}

	public GenericDirectedEdgeNtro(N fromNode, EdgeTypeNtro edgeType, N toNode) {
		super(fromNode, edgeType, toNode);
	}

	public GenericDirectedEdgeNtro(N fromNode, String edgeName, N toNode) {
		super(fromNode, new EdgeTypeNtro(Direction.FORWARD, edgeName), toNode);
	}


}
