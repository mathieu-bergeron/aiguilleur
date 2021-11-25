package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.GenericGraphStructure;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphBuilderNtro<NV extends NodeValue, 
                                              EV extends EdgeValue, 
                                              GS extends GenericGraphStructure<NV,EV>,
                                              G extends GenericGraph<NV,EV>> 

       extends        GenericGraphNtro<NV, EV>
       implements     GenericGraphBuilder<NV,EV,GS,G>, 
                      GenericGraph<NV,EV> {

	private GraphId id;

	private GS graphStructure = createGraphStructure();
	
	protected abstract GS createGraphStructure();

	protected GraphId getId() {
		return id;
	}

	protected void setId(GraphId id) {
		this.id = id;
	}

	@Override
	public GraphId id() {
		return id;
	}

	@Override
	public String label() {
		return id.toHtmlId();
	}

	protected GS getGraphStructure() {
		return graphStructure;
	}

	protected void setGraphStructure(GS graphStructure) {
		this.graphStructure = graphStructure;
	}

	public GenericGraphBuilderNtro() {
		setId(GraphId.newGraphId());
	}

	public GenericGraphBuilderNtro(String graphName) {
		setId(GraphId.fromGraphName(graphName));
	}

	@Override
	public Node<NV> addNode(NV nodeValue) {

		Node<NV> node = getGraphStructure().createNode(nodeValue);
		
		if(getGraphStructure().containsNode(node)) {

			Ntro.exceptionThrower().throwException(new NodeAlreadyAddedException("NodeId already taken: " + node.id().toKey()));

		}else {
			
			getGraphStructure().memorizeNode(node);

		}

		return node;
	}

	@Override
	public Edge<EV> addEdge(Node<NV> from, EV edgeValue, Node<NV> to) {
		getGraphStructure().memorizeNode(from);
		getGraphStructure().memorizeNode(to);
		
		Edge<EV> edge = getGraphStructure().createEdge(from, edgeValue, to);
		
		if(getGraphStructure().containsEdge(edge)) {

			Ntro.exceptionThrower().throwException(new EdgeAlreadyAddedException("EdgeId already taken: " + edge.id().toKey()));
			
		}else {
			
			getGraphStructure().memorizeEdge(from, edge, to);
			
		}
		
		detectCycleFrom(from);
		
		return edge;
	}

	protected abstract void detectCycleFrom(Node<NV> from);

	@Override
	protected <R> void _reduceStartNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		getGraphStructure().reduceStartNodes(result, reducer);
	}

	@Override
	protected <R> void _reduceNextSteps(Node<NV> fromNode, 
			                            ResultNtro<R> result, 
			                            StepReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		getGraphStructure().reduceNextSteps(fromNode, result, reducer);
	}

	@Override
	protected <R> void _walkStep(Node<NV> fromNode, 
								 Step step,
			                     ResultNtro<R> result, 
			                     ReachableEdgeReducer<NV, EV, R> reducer) {
		
		if(result.hasException()) {
			return;
		}

		getGraphStructure().walkStep(fromNode, step, result, reducer);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public G toGraph() {
		return (G) this;
	}

}
