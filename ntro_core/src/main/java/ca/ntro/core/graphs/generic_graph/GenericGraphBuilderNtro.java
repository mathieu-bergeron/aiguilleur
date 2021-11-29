package ca.ntro.core.graphs.generic_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeNotFoundException;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphBuilderNtro<N extends Node<N,E,SO>,
                                              E extends Edge<N,E,SO>,
                                              SO extends SearchOptions,
                                              G extends GenericGraph<N,E,SO>> 

       extends        GenericGraphNtro<N,E,SO>
       implements     GenericGraphBuilder<N,E,SO,G>, 
                      GenericGraph<N,E,SO> {

	private GraphId id;
	private Map<String, N> nodes = new HashMap<>();

	public GraphId getId() {
		return id;
	}

	public void setId(GraphId id) {
		this.id = id;
	}

	public Map<String, N> getNodes() {
		return nodes;
	}

	public void setNodes(Map<String, N> nodes) {
		this.nodes = nodes;
	}

	@Override
	public GraphId id() {
		return id;
	}

	@Override
	public String label() {
		return id.toHtmlId();
	}

	public GenericGraphBuilderNtro() {
		setId(GraphId.newGraphId());
	}

	public GenericGraphBuilderNtro(String graphName) {
		setId(GraphId.fromGraphName(graphName));
	}


	@Override
	public void addNode(N node) {
		getNodes().put(node.id().toKey().toString(), node);
	}

	@Override
	protected InternalGraphWriter<N, E, SO> internalGraphWriter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SearchOptions defaultSearchOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public N findNode(NodeId id) {
		N node = getNodes().get(id.toKey().toString());
		
		if(node == null) {
			Ntro.exceptionThrower().throwException(new NodeNotFoundException("Node not found for key: " + id.toKey()));
		}
		
		return node;
	}

	@Override
	protected <R> void _reduceStartNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer) {
		_reduceNodes(result, reducer);
	}

	protected <R> void _reduceNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		for(N node : nodes.values()) {
			
			try {
				
				result.registerValue(reducer.reduceNode(result.value(), node));

			}catch(Throwable t) {
				
				result.registerException(t);
				return;
			}
		}
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<N, E, SO, R> reducer) {
		ResultNtro<R> result = new ResultNtro<>(initialValue);

		_reduceNodes(result, reducer);
		
		return result;
	}

	
	/*

	@Override
	public N addNode(NV nodeValue) {

		N node = getGraphStructure().createNode(nodeValue);
		
		if(getGraphStructure().containsNode(node)) {

			Ntro.exceptionThrower().throwException(new NodeAlreadyAddedException("NodeId already taken: " + node.id().toKey()));

		}else {
			
			getGraphStructure().memorizeNode(node);

		}

		return node;
	}

	@Override
	public E addEdge(N from, EV edgeValue, N to) {
		getGraphStructure().memorizeNode(from);
		getGraphStructure().memorizeNode(to);
		
		Step<NV,EV,N,E> walkedStep = getGraphStructure().createWalkedStep(Direction.FORWARD, from, edgeValue, to);

		if(getGraphStructure().containsStep(walkedStep)) {

			Ntro.exceptionThrower().throwException(new EdgeAlreadyAddedException("WalkedStep already exists: " + walkedStep));
			
		}else {
			
			getGraphStructure().memorizeStep(walkedStep);
			
		}
		
		detectCycleFrom(from);
		
		return walkedStep.edge();
	}

	protected abstract void detectCycleFrom(N from);

	@Override
	protected <R> void _reduceStartNodes(ResultNtro<R> result, NodeReducer<NV,N,R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		getGraphStructure().reduceStartNodes(result, reducer);
	}

	@Override
	protected <R> void _reduceNextStepIds(N fromNode, 
			                            ResultNtro<R> result, 
			                            EdgeNameReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		getGraphStructure().reduceNextEdgeIds(fromNode, result, reducer);
	}

	@Override
	protected <R> void _reduceNextStepsById(N fromNode, 
								 StepId stepId,
			                     ResultNtro<R> result, 
			                     StepReducer<NV,EV,N,E,R> reducer) {
		
		if(result.hasException()) {
			return;
		}

		getGraphStructure().reduceNextEdgesById(fromNode, stepId, result, reducer);
	}
	*/
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public G toGraph() {
		return (G) this;
	}


}
