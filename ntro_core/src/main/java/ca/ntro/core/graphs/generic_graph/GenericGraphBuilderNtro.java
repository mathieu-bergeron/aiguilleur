package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeAlreadyAddedException;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeAlreadyAddedException;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.WalkedStep;
import ca.ntro.core.graphs.WalkedStepReducer;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.GenericGraphStructure;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphBuilderNtro<NV extends NodeValue, 
                                              EV extends EdgeValue, 
                                              N extends Node<NV>,
                                              E extends Edge<EV>,
                                              GS extends GenericGraphStructure<NV,EV,N,E>,
                                              G extends GenericGraph<NV,EV,N,E>> 

       extends        GenericGraphNtro<NV,EV,N,E>
       implements     GenericGraphBuilder<NV,EV,N,E,GS,G>, 
                      GenericGraph<NV,EV,N,E> {

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
		
		WalkedStep<NV,EV,N,E> walkedStep = getGraphStructure().createWalkedStep(Direction.FORWARD, from, edgeValue, to);

		if(getGraphStructure().containsWalkedStep(walkedStep)) {

			Ntro.exceptionThrower().throwException(new EdgeAlreadyAddedException("WalkedStep already exists: " + walkedStep));
			
		}else {
			
			getGraphStructure().memorizeWalkedStep(walkedStep);
			
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
	protected <R> void _reduceNextSteps(N fromNode, 
			                            ResultNtro<R> result, 
			                            StepReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		getGraphStructure().reduceNextSteps(fromNode, result, reducer);
	}

	@Override
	protected <R> void _walkStep(N fromNode, 
								 Step step,
			                     ResultNtro<R> result, 
			                     WalkedStepReducer<NV,EV,N,E,R> reducer) {
		
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
