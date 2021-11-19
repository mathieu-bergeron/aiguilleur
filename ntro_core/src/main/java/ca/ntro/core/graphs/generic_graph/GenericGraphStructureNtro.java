package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.EdgesForFromNode;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.GenericGraphStructure;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class  GenericGraphStructureNtro<NV extends NodeValue, EV extends EdgeValue> 
       implements      GenericGraphStructure<NV,EV> {
	
	
	protected abstract EdgesForFromNode<NV,EV> edgesByDirection(Direction direction);

	@Override
	public void addEdge(Direction direction, Node<NV> from, Edge<EV> edge, Node<NV> to) {
		EdgesForFromNode<NV,EV> edges = edgesByDirection(direction);

		if(edges != null) {
			edges.addEdge(from, edge, to);
		}
	}

	@Override
	public <R> void reduceEdgeNames(Node<NV> fromNode, 
			                        Direction direction, 
			                        ResultNtro<R> result, 
			                        EdgeNameReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		EdgesForFromNode<NV,EV> edges = edgesByDirection(direction);

		if(edges != null) {
			edges.reduceEdgeNames(fromNode, result, reducer);
		}
	}

	@Override
	public <R> void reduceEdgesByName(Node<NV> fromNode, 
			                          Direction direction, 
			                          String edgeName, 
			                          ResultNtro<R> result, 
			                          ReachableEdgeReducer<NV, EV, R> reducer) {

		if(result.hasException()) {
			return;
		}

		EdgesForFromNode<NV,EV> edges = edgesByDirection(direction);

		if(edges != null) {
			edges.reduceEdgesByName(fromNode, edgeName, result, reducer);
		}
	}
}
