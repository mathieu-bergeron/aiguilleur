package ca.ntro.core.graphs.graph;

import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class      GraphNtro<NV extends NodeValue, EV extends EdgeValue> 
       extends    GenericGraphBuilderNtro<NV,EV,GraphStructure<NV,EV>,Graph<NV,EV>> 
       implements Graph<NV,EV>, GraphBuilder<NV,EV> {


	public GraphNtro() {
		super();
	}

	public GraphNtro(String graphName) {
		super(graphName);
	}

	@Override
	protected GraphStructure<NV, EV> createGraphStructure() {
		return new GraphStructureNtro<NV,EV>();
	}

	@Override
	protected GraphSearchOptions defaultSearchOptions() {
		return new GraphSearchOptions();
	}

	@Override
	protected void detectCycleFrom(Node<NV> from) {
	}

	@Override
	protected EdgeId newEdgeId(Node<NV> from, EV edgeValue, Node<NV> to) {
		EdgeId edgeId = null;

		if(from.id().toKey().compareTo(to.id().toKey()) < 0) {
			
			edgeId = new EdgeId(from.id(), edgeValue.name(), to.id());

		}else {

			edgeId = new EdgeId(to.id(), edgeValue.name(), from.id());

		}
		
		return edgeId;
	}

	@Override
	protected void addEdgeToGraphStructure(Node<NV> from, Edge<EV> edge, Node<NV> to) {
		if(from.id().toKey().compareTo(to.id().toKey()) < 0) {
			
			getGraphStructure().addEdge(Direction.FORWARD, from, edge, to);
			
		}else {

			getGraphStructure().addEdge(Direction.FORWARD, to, edge, from);
		}
	}
}
