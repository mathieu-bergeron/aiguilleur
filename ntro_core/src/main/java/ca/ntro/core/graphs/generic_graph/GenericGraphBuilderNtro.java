package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeType;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphBuilderNtro<N extends Node<N,E,SO>,
                                              E extends Edge<N,E,SO>,
                                              SO extends SearchOptions,
                                              G extends GenericGraph<N,E,SO>> 

       extends        GenericGraphNtro<N,E,SO>
       implements     GenericGraphBuilder<N,E,SO,G>, 
                      GenericGraph<N,E,SO> {

	private GraphId id;

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

	public GenericGraphBuilderNtro() {
		setId(GraphId.newGraphId());
	}

	public GenericGraphBuilderNtro(String graphName) {
		setId(GraphId.fromGraphName(graphName));
	}

	@Override
	protected <R> void _reduceStartNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNode(N node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(E edge) {
		// TODO Auto-generated method stub
		
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
