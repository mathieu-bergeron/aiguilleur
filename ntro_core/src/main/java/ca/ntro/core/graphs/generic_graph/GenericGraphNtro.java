package ca.ntro.core.graphs.generic_graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.StepsReducer;
import ca.ntro.core.graphs.StepsVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeMatcher;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.ReachableStepReducer;
import ca.ntro.core.graphs.ReachableStepVisitor;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.SearchOptionsNtro;
import ca.ntro.core.graphs.SearchStrategy;
import ca.ntro.core.graphs.Step;
import ca.ntro.core.graphs.StepNtro;
import ca.ntro.core.graphs.WalkedStep;
import ca.ntro.core.graphs.WalkedStepReducer;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.util.ListUtils;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphNtro<NV extends NodeValue, EV extends EdgeValue>
       implements     GenericGraph<NV,EV> {

	@Override
	public abstract GraphId id();

	@Override
	public abstract String label();

	@Override
	public void write(GraphWriter writer) {
		internalGraphWriter().write(this, writer);
	}

	protected abstract InternalGraphWriter<NV,EV> internalGraphWriter();

	protected abstract SearchOptions defaultSearchOptions();

	protected abstract <R> void _reduceStartNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer);

	protected abstract <R> void _reduceNextSteps(Node<NV> fromNode, ResultNtro<R> result, StepReducer<R> reducer);

	protected abstract <R> void _walkStep(Node<NV> fromNode, Step step, ResultNtro<R> result, WalkedStepReducer<NV, EV, R> reducer);
	

	@Override
	public Node<NV> findNode(NodeId id) {
		return findNode(new NodeMatcher<NV>() {
			@Override
			public boolean matches(Node<NV> node) {
				return node.id().equals(id);
			}
		});
	}

	@Override
	public Node<NV> findNode(NodeMatcher<NV> matcher) {

		Result<Node<NV>> result = reduceNodes(null, (accumulator, n) -> {
			if(accumulator != null) {
				throw new Break();
			}

			if(matcher.matches(n)) {
				return n;
			}

			return accumulator;
		});

		result.throwException();

		return result.value();
	}

	@Override
	public Node<NV> findNode(NV nodeValue) {
		return findNode(new NodeMatcher<NV>() {
			@Override
			public boolean matches(Node<NV> node) {
				return node.value().equals(nodeValue);
			}
		});
	}

	@Override
	public Node<NV> findNode(String rawNodeId) {
		return findNode(new NodeId(rawNodeId));
	}

	@Override
	public void forEachStartNode(NodeVisitor<NV> visitor) {
		reduceStartNodes(null, (__, n) ->{
			
			visitor.visitNode(n);
			
			return null;

		}).throwException();;
	}


	@Override
	public <R> Result<R> reduceStartNodes(R initialValue, NodeReducer<NV, R> reducer){

		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceStartNodes(result, reducer);
		
		return result;
	}


	@Override
	public void forEachNode(NodeVisitor<NV> visitor) {
		reduceNodes(null, (accumulator, n) -> {
			
			visitor.visitNode(n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<NV, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceNodes(result, reducer);
		
		return result;
	}

	protected <R> void _reduceNodes(ResultNtro<R> result, NodeReducer<NV, R> reducer) {
		
		Set<String> visitedNodes = new HashSet<>();
		
		_reduceStartNodes(result, (__, startNode) -> {
			if(visitedNodes.contains(startNode.id().toKey())) {
				return result.value();
			}
			
			visitedNodes.add(startNode.id().toKey());

			try {
			
				result.registerValue(reducer.reduceNode(result.value(), startNode));

			}catch(Throwable t) {
				
				result.registerException(t);
			}

			_reduceReachableNodes(startNode, defaultSearchOptions(), result, (___, walkedEdges, n) ->{
				if(visitedNodes.contains(n.id().toKey())) {
					return result.value();
				}
				
				visitedNodes.add(n.id().toKey());
				
				try {
				
					result.registerValue(reducer.reduceNode(result.value(), n));

				}catch(Throwable t) {
					
					result.registerException(t);
				}

				return result.value();
			});
			
			return result.value();
		});
	}

	@Override
	public void forEachEdge(EdgeVisitor<NV, EV> visitor) {
		reduceEdges(null, (accumulator, from, edge, to) -> {
			
			visitor.visitEdge(from, edge, to);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<NV, EV, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceEdges(result, reducer);
		
		return result;
	}

	protected <R> void _reduceEdges(ResultNtro<R> result, EdgeReducer<NV, EV, R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		Set<String> visitedEdges = new HashSet<>();

		_reduceStartNodes(result, (__, n) -> {

			_reduceReachableEdges(n, defaultSearchOptions(), result, (___, walkedEdges, step) -> {
				
				if(!visitedEdges.contains(step.edge().id().toKey())) {
					
					try {

						result.registerValue(reducer.reduceEdge(result.value(), step.from(), step.edge(), step.to()));

					}catch(Throwable t) {
						
						result.registerException(t);
					}
					
					visitedEdges.add(step.edge().id().toKey());
				}
				
				return result.value();
			});

			return result.value();
		});
	}

	@Override
	public void forEachReachableNode(Node<NV> fromNode, ReachableNodeVisitor<NV, EV> visitor) {
		forEachReachableNode(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableNode(Node<NV> fromNode, SearchOptions options, ReachableNodeVisitor<NV, EV> visitor) {
		reduceReachableNodes(fromNode, options, null, (accumulator, walkedSteps, n) -> {

			visitor.visitReachableNode(walkedSteps, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> fromNode, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<NV, EV, R> reducer) {
		
		return reduceReachableNodes(fromNode, defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<NV> fromNode, 
			                                  SearchOptions options, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<NV, EV, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceReachableNodes(fromNode, options, result, reducer);
		
		
		return result;
	}

	protected <R> void _reduceReachableNodes(Node<NV> fromNode, 
			                                 SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableNodeReducer<NV, EV, R> reducer) {

		if(result.hasException()) {
			return;
		}
		
		Set<String> visitedNodes = new HashSet<>();
		
		_reduceReachableEdges(fromNode, options, result, (__, walkedEdges, step) -> {
			
			if(!visitedNodes.contains(step.to().id().toKey())) {
				
				try {
					
					result.registerValue(reducer.reduceReachableNode(result.value(), walkedEdges, step.to()));

				} catch(Throwable t) {
					
					result.registerException(t);
					return result.value();
				}

				visitedNodes.add(step.to().id().toKey());
			}
			
			return result.value();
		});
	}
	
	protected <R> void _reduceNextEdges(Node<NV> fromNode, SearchOptions options, ResultNtro<R> result, ReachableStepReducer<NV, EV, R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		_reduceNextSteps(fromNode, result, (__, step) -> {

			_walkStep(fromNode, step, result, (___, walkedStep) -> {

				reducer.reduceWalkedStep(result.value(), new ArrayList<>(), walkedStep);
				
				return result.value();
			});
			
			return result.value();
		});
	}


	@Override
	public void forEachReachableStep(Node<NV> fromNode, ReachableStepVisitor<NV, EV> visitor) {
		forEachReachableStep(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableStep(Node<NV> fromNode, SearchOptions options, ReachableStepVisitor<NV, EV> visitor) {
		reduceReachableSteps(fromNode, options, null, (accumulator, previousSteps, currentStep) -> {

			visitor.visitReachableStep(previousSteps, currentStep);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceReachableSteps(Node<NV> fromNode, R initialValue, ReachableStepReducer<NV, EV, R> reducer) {
		
		return reduceReachableSteps(fromNode, defaultSearchOptions(), initialValue, reducer);
	}


	@Override
	public <R> Result<R> reduceReachableSteps(Node<NV> fromNode, 
			                                  SearchOptions options, 
			                                  R initialValue, 
			                                  ReachableStepReducer<NV, EV, R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceReachableEdges(fromNode, options, result, reducer);
		
		return result;
	}

	protected <R> void _reduceReachableEdges(Node<NV> fromNode, 
			
			                                 SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableStepReducer<NV, EV, R> reducer) {
		
		if(options.searchStrategy() == SearchStrategy.DEPTH_FIRST_SEARCH) {

			_reduceReachableEdgesDepthFirst(fromNode, options, new HashSet<>(), result, reducer);

		}else {

			_reduceReachableEdgesBreadthFirst(fromNode, options, new HashSet<>(), result, reducer);
		}
	}

	protected <R> void _reduceReachableEdgesDepthFirst(Node<NV> fromNode, 
			                                           SearchOptions options, 
			                                           Set<String> visitedEdges,
			                                           ResultNtro<R> result, 
			                                           ReachableStepReducer<NV, EV, R> reducer) {

		if(result.hasException()) {
			return;
		}

		_reduceNextSteps(fromNode, result, (__, step) -> {
			
			_walkStep(fromNode, step, result, (___, walkedStep) -> {
				if(visitedEdges.contains(walkedStep.edge().id().toKey())) {
					return result.value();
				}
				
				List<WalkedStep<NV,EV>> walkedSteps = new ArrayList<>();
				walkedSteps.add(walkedStep);

				if(options.maxDistance().hasValue() 
						&& walkedSteps.size() > options.maxDistance().value()) {
					return result.value();
				}
				
				try {
				
					result.registerValue(reducer.reduceWalkedStep(result.value(), walkedSteps, walkedStep));

				}catch(Throwable t) {
					
					result.registerException(t);
					return result.value();
				}
				
				visitedEdges.add(walkedStep.edge().id().toKey());

				_reduceReachableEdgesDepthFirst(walkedStep.to(), options, visitedEdges, result, reducer);

				return result.value();
			});
			
			return result.value();
		});
	}

	protected <R> void _reduceReachableEdgesBreadthFirst(Node<NV> fromNode, 
			                                             SearchOptions options, 
			                                             Set<String> visitedEdges,
			                                             ResultNtro<R> result, 
			                                             ReachableStepReducer<NV, EV, R> reducer) {
		
		SearchOptions oneStepOptions = new SearchOptionsNtro(options.searchDirections(), 1);
		
		_reduceReachableEdgesBreadthFirst(fromNode, options, oneStepOptions, visitedEdges, new ArrayList<>(), result, reducer);
	}

	protected <R> void _reduceReachableEdgesBreadthFirst(Node<NV> fromNode, 
			                                             SearchOptions options, 
			                                             SearchOptions oneStepOptions,
			                                             Set<String> visitedEdges,
			                                             List<WalkedStep<NV,EV>> walkedSteps,
			                                             ResultNtro<R> result, 
			                                             ReachableStepReducer<NV, EV, R> reducer) {

		if(options.maxDistance().hasValue() 
				&& walkedSteps.size() + 1 > options.maxDistance().value()) {
			return;
		}

		_reduceReachableEdgesDepthFirst(fromNode, oneStepOptions, visitedEdges, result, reducer);

		_reduceReachableEdgesDepthFirst(fromNode, oneStepOptions, visitedEdges, result, (__, ___, walkedStep) -> {
			
			List<WalkedStep<NV,EV>> newWalkedEdges = new ArrayList<>(walkedSteps);
			newWalkedEdges.add(walkedStep);

			_reduceReachableEdgesBreadthFirst(walkedStep.to(), options, oneStepOptions, visitedEdges, newWalkedEdges, result, reducer);
			
			return result.value();
		});
	}

	@Override
	public void visitSteps(Node<NV> fromNode, List<Step> steps, StepsVisitor<NV, EV> visitor) {
		reduceSteps(fromNode, steps, null, (accumulator, walkedEdges, remainingSteps, n) -> {
			
			visitor.visitStep(walkedEdges, remainingSteps, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceSteps(Node<NV> fromNode, 
			                         List<Step> steps, 
			                         R initialValue, 
			                         StepsReducer<NV, EV, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceEdgeWalk(fromNode, steps, new ArrayList<>(), result, reducer);

		return result;
	}

	protected <R> void _reduceEdgeWalk(Node<NV> fromNode, 
									   List<Step> steps,
			                           List<WalkedStep<NV,EV>> walkedSteps,
			                           ResultNtro<R> result, 
			                           StepsReducer<NV, EV, R> reducer) {
		
		if(result.hasException()) {
			return;
		}
		
		if(steps.isEmpty()) {
			return;
		}
		
		Step nextStep = steps.get(0);
		List<Step> remainingSteps = ListUtils.subList(steps, 1);
		
		_walkStep(fromNode, nextStep, result, (__, walkedStep) -> {

			try {
				
				List<WalkedStep<NV,EV>> newWalkedSteps = new ArrayList<>(walkedSteps);
				walkedSteps.add(walkedStep);

				result.registerValue(reducer.reduceStep(result.value(), newWalkedSteps, remainingSteps, walkedStep.to()));
				
				_reduceEdgeWalk(walkedStep.to(), steps, newWalkedSteps, result, reducer);

			}catch(Throwable t) {
				
				result.registerException(t);
				
			}
			
			return result.value();
		});
	}
	
	

}
