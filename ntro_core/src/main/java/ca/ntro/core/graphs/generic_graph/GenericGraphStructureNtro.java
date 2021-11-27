package ca.ntro.core.graphs.generic_graph;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.WalkedStep;
import ca.ntro.core.graphs.WalkedStepReducer;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.EdgesForFromNode;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.GenericGraphStructure;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.StepsByDirection;
import ca.ntro.core.graphs.generic_graph.generic_graph_structure.StepsByDirectionNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class  GenericGraphStructureNtro<NV extends NodeValue, 
                                                 EV extends EdgeValue,
                                                 N extends Node<NV>,
                                                 E extends Edge<EV>> 

       implements      GenericGraphStructure<NV,EV,N,E> {
	
	private Map<String, N> nodes = new HashMap<>();
	private Map<String, E> edges = new HashMap<>();
	
	private StepsByDirection<NV,EV,N,E> steps = new StepsByDirectionNtro<NV,EV,N,E>();
	
	public StepsByDirection<NV, EV, N, E> getSteps() {
		return steps;
	}

	public void setSteps(StepsByDirection<NV, EV, N, E> steps) {
		this.steps = steps;
	}

	protected Map<String,N> getNodes() {
		return nodes;
	}

	protected void setNodes(Map<String,N> nodes) {
		this.nodes = nodes;
	}

	protected Map<String,E> getEdges() {
		return edges;
	}

	protected void setEdges(Map<String,E> edges) {
		this.edges = edges;
	}

	protected abstract EdgesForFromNode<NV,EV,N,E> edgesByDirection(Direction direction);

	@SuppressWarnings("unchecked")
	@Override
	public WalkedStep<NV,EV,N,E> createWalkedStep(Direction direction, N from, EV edgeValue, N to) {

		WalkedStep<NV,EV,N,E> walkedStep = null;
		
		if(step.direction() == Direction.FORWARD
				|| step.direction() == Direction.BACKWARD) {

			EdgeId edgeId = directedEdgeId(from, step.name(), to);
			
			E edge = (E) new EdgeNtro<EV>(edgeId, edgeValue);

			walkedStep = new WalkedStepNtro<NV,EV,N,E>();
		}
		
		


		return edge;
	}

	protected abstract EdgeId directedEdgeId(N from, EV edgeValue, N to);
	
	@Override
	public void memorizeWalkedStep(WalkedStep<NV,EV,N,E> step) {
		steps.memorizeStep(step);
	}

	protected abstract void memorizeDirectedEdge(N from, E edge, N to);

	@Override
	public <R> void reduceNextSteps(N fromNode, 
			                        ResultNtro<R> result, 
			                        StepReducer<R> reducer) {

		if(result.hasException()) {
			return;
		}

		for(Direction direction : Direction.values()) {

			EdgesForFromNode<NV,EV,N,E> edges = edgesByDirection(direction);

			if(edges != null) {
				edges.reduceNextSteps(fromNode, direction, result, reducer);
			}
		}
	}

	@Override
	public <R> void walkStep(N fromNode, 
						     Step step,
							 ResultNtro<R> result, 
							 WalkedStepReducer<NV,EV,N,E,R> reducer) {

		if(result.hasException()) {
			return;
		}

		EdgesForFromNode<NV,EV,N,E> edges = edgesByDirection(step.direction());

		if(edges != null) {
			edges.walkStep(fromNode, step, result, reducer);
		}
	}

	@Override
	public void memorizeNode(N node) {
		getNodes().put(node.id().toKey(), node);
	}

	@Override
	public <R> void reduceStartNodes(ResultNtro<R> result, NodeReducer<NV,N,R> reducer) {
		if(result.hasException()) {
			return;
		}

		for(String nodeKey : nodes.keySet()) {

			N node = getNodes().get(nodeKey);

			try {

				result.registerValue(reducer.reduceNode(result.value(), node));

			} catch (Throwable e) {

				result.registerException(e);
				return;
			}
		}
	}

	@Override
	public boolean containsNode(N node) {
		return getNodes().containsKey(node.id().toKey());
	}

	@Override
	public boolean containsEdge(E edge) {
		return getEdges().containsKey(edge.id().toKey());
	}

	@SuppressWarnings("unchecked")
	@Override
	public N createNode(NV nodeValue) {

		NodeId nodeId = new NodeId(nodeValue.name().toKey());

		N node = (N) new NodeNtro<NV>(nodeId, nodeValue);
		
		return node;
	}
}
