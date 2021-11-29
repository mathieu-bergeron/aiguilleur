package ca.ntro.core.graphs.generic_graph;


import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphNtro<N extends Node<N,E,SO>, 
                                       E extends Edge<N,E,SO>,
                                       SO extends SearchOptions> 


                                       

       implements     GenericGraph<N,E,SO> {

	
	

	@Override
	public abstract GraphId id();

	@Override
	public abstract String label();

	@Override
	public void write(GraphWriter writer) {
		internalGraphWriter().write(this, writer);
	}

	protected abstract InternalGraphWriter<N,E,SO> internalGraphWriter();

	protected abstract SO defaultSearchOptions();

	protected abstract <R> void _reduceStartNodes(ResultNtro<R> result, NodeReducer<N, E, SO, R> reducer);

	@Override
	public N findNode(NodeId id) {
		Result<N> result = reduceNodes(null, (accumulator, n) -> {
			if(accumulator != null) {
				throw new Break();
			}

			if(n.id().equals(id)) {
				accumulator = n;
			}

			return accumulator;
		});

		result.throwException();

		return result.value();
	}

	@Override
	public <R> Result<R> reduceStartNodes(R initialValue, NodeReducer<N, E, SO, R> reducer){
		ResultNtro<R> result = new ResultNtro<>(initialValue);
		
		_reduceStartNodes(result, reducer);

		return result;
	}

	@Override
	public void forEachStartNode(NodeVisitor<N, E, SO> visitor) {
		reduceStartNodes(null, (__, n) ->{
			
			visitor.visitNode(n);
			
			return null;

		}).throwException();
	}

	@Override
	public void forEachNode(NodeVisitor<N, E, SO> visitor) {
		reduceNodes(null, (accumulator, n) -> {
			
			visitor.visitNode(n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<N, E, SO, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceNodes(result, reducer);
		
		return result;
	}

	protected <R> void _reduceNodes(ResultNtro<R> result, NodeReducer<N,E,SO,R> reducer) {

		Set<String> visitedNodes = new HashSet<>();
		
		forEachStartNode(startNode -> {
			if(visitedNodes.contains(startNode.id().toKey().toString())) {
				throw new Break();
			}

			visitedNodes.add(startNode.id().toKey().toString());

			try {
			
				result.registerValue(reducer.reduceNode(result.value(), startNode));

			}catch(Throwable t) {

				result.registerException(t);
				throw new Break();
			}

			startNode.forEachReachableNode(defaultSearchOptions(), (walked, n) -> {
				if(visitedNodes.contains(n.id().toKey().toString())) {
					throw new Break();
				}

				visitedNodes.add(n.id().toKey().toString());

				try {
				
					result.registerValue(reducer.reduceNode(result.value(), n));

				}catch(Throwable t) {

					result.registerException(t);
					throw new Break();
				}
			});
		});
	}

	@Override
	public void forEachEdge(EdgeVisitor<N, E, SO> visitor) {
		reduceEdges(null, (accumulator, edge) -> {
			
			visitor.visitEdge(edge);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<N, E, SO, R> reducer) {
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceEdges(result, reducer);
		
		return result;
	}

	protected <R> void _reduceEdges(ResultNtro<R> result, EdgeReducer<N,E,SO,R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		Set<String> visitedEdges = new HashSet<>();
		
		forEachNode(n -> {
			
			n.forEachEdge(e -> {
				if(visitedEdges.contains(e.id().toKey().toString())) {
					throw new Break();
				}
				
				visitedEdges.add(e.id().toKey().toString());

				try {
				
					result.registerValue(reducer.reduceEdge(result.value(), e));

				}catch(Throwable t) {

					result.registerException(t);
					throw new Break();
				}
			});
		});
	}
	
	/*
	


	@Override
	public void forEachEdge(EdgeVisitor<NV,EV,N,E> visitor) {
		reduceEdges(null, (accumulator, from, edge, to) -> {
			
			visitor.visitEdge(from, edge, to);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceEdges(R initialValue, EdgeReducer<NV,EV,N,E,R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceEdges(result, reducer);
		
		return result;
	}

	protected <R> void _reduceEdges(ResultNtro<R> result, EdgeReducer<NV,EV,N,E,R> reducer) {
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
					
					visitedEdges.add(step.edge().id().toKey().toString());
				}
				
				return result.value();
			});

			return result.value();
		});
	}

	@Override
	public void forEachReachableNode(N fromNode, ReachableNodeVisitor<NV,EV,N,E> visitor) {
		forEachReachableNode(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableNode(N fromNode, SearchOptions options, ReachableNodeVisitor<NV,EV,N,E> visitor) {
		reduceReachableNodes(fromNode, options, null, (accumulator, walkedSteps, n) -> {

			visitor.visitReachableNode(walkedSteps, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceReachableNodes(N fromNode, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<NV,EV,N,E,R> reducer) {
		
		return reduceReachableNodes(fromNode, defaultSearchOptions(), initialValue, reducer);
	}

	@Override
	public <R> Result<R> reduceReachableNodes(N fromNode, 
			                                  SearchOptions options, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<NV,EV,N,E,R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceReachableNodes(fromNode, options, result, reducer);
		
		
		return result;
	}

	protected <R> void _reduceReachableNodes(N fromNode, 
			                                 SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableNodeReducer<NV,EV,N,E,R> reducer) {

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

				visitedNodes.add(step.to().id().toKey().toString());
			}
			
			return result.value();
		});
	}
	
	protected <R> void _reduceNextEdges(N fromNode, SearchOptions options, ResultNtro<R> result, ReachableStepReducer<NV,EV,N,E,R> reducer) {
		if(result.hasException()) {
			return;
		}
		
		_reduceNextStepIds(fromNode, result, (__, step) -> {

			_reduceNextStepsById(fromNode, step, result, (___, walkedStep) -> {

				reducer.reduceWalkedStep(result.value(), new ArrayList<>(), walkedStep);
				
				return result.value();
			});
			
			return result.value();
		});
	}


	@Override
	public void forEachReachableStep(N fromNode, ReachableStepVisitor<NV,EV,N,E> visitor) {
		forEachReachableStep(fromNode, defaultSearchOptions(), visitor);
	}

	@Override
	public void forEachReachableStep(N fromNode, SearchOptions options, ReachableStepVisitor<NV,EV,N,E> visitor) {
		reduceReachableSteps(fromNode, options, null, (accumulator, previousSteps, currentStep) -> {

			visitor.visitReachableStep(previousSteps, currentStep);

			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceReachableSteps(N fromNode, R initialValue, ReachableStepReducer<NV,EV,N,E,R> reducer) {
		
		return reduceReachableSteps(fromNode, defaultSearchOptions(), initialValue, reducer);
	}


	@Override
	public <R> Result<R> reduceReachableSteps(N fromNode, 
			                                  SearchOptions options, 
			                                  R initialValue, 
			                                  ReachableStepReducer<NV,EV,N,E,R> reducer) {

		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		_reduceReachableEdges(fromNode, options, result, reducer);
		
		return result;
	}

	protected <R> void _reduceReachableEdges(N fromNode, 
			                                 SearchOptions options, 
			                                 ResultNtro<R> result, 
			                                 ReachableStepReducer<NV,EV,N,E,R> reducer) {
		
		if(options.searchStrategy() == SearchStrategy.DEPTH_FIRST_SEARCH) {

			_reduceReachableEdgesDepthFirst(fromNode, options, new HashSet<>(), result, reducer);

		}else {

			_reduceReachableEdgesBreadthFirst(fromNode, options, new HashSet<>(), result, reducer);
		}
	}

	protected <R> void _reduceReachableEdgesDepthFirst(N fromNode, 
			                                           SearchOptions options, 
			                                           Set<String> visitedEdges,
			                                           ResultNtro<R> result, 
			                                           ReachableStepReducer<NV,EV,N,E,R> reducer) {

		if(result.hasException()) {
			return;
		}

		_reduceNextStepIds(fromNode, result, (__, step) -> {
			
			_reduceNextStepsById(fromNode, step, result, (___, walkedStep) -> {
				if(visitedEdges.contains(walkedStep.edge().id().toKey())) {
					return result.value();
				}
				
				List<Step<NV,EV,N,E>> walkedSteps = new ArrayList<>();
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
				
				visitedEdges.add(walkedStep.edge().id().toKey().toString());

				_reduceReachableEdgesDepthFirst(walkedStep.to(), options, visitedEdges, result, reducer);

				return result.value();
			});
			
			return result.value();
		});
	}

	protected <R> void _reduceReachableEdgesBreadthFirst(N fromNode, 
			                                             SearchOptions options, 
			                                             Set<String> visitedEdges,
			                                             ResultNtro<R> result, 
			                                             ReachableStepReducer<NV,EV,N,E,R> reducer) {
		
		SearchOptions oneStepOptions = new SearchOptionsNtro(options.searchDirections(), 1);
		
		_reduceReachableEdgesBreadthFirst(fromNode, options, oneStepOptions, visitedEdges, new ArrayList<>(), result, reducer);
	}

	protected <R> void _reduceReachableEdgesBreadthFirst(N fromNode, 
			                                             SearchOptions options, 
			                                             SearchOptions oneStepOptions,
			                                             Set<String> visitedEdges,
			                                             List<Step<NV,EV,N,E>> walkedSteps,
			                                             ResultNtro<R> result, 
			                                             ReachableStepReducer<NV,EV,N,E,R> reducer) {

		if(options.maxDistance().hasValue() 
				&& walkedSteps.size() + 1 > options.maxDistance().value()) {
			return;
		}

		_reduceReachableEdgesDepthFirst(fromNode, oneStepOptions, visitedEdges, result, reducer);

		_reduceReachableEdgesDepthFirst(fromNode, oneStepOptions, visitedEdges, result, (__, ___, walkedStep) -> {
			
			List<Step<NV,EV,N,E>> newWalkedEdges = new ArrayList<>(walkedSteps);
			newWalkedEdges.add(walkedStep);

			_reduceReachableEdgesBreadthFirst(walkedStep.to(), options, oneStepOptions, visitedEdges, newWalkedEdges, result, reducer);
			
			return result.value();
		});
	}

	@Override
	public void visitSteps(N fromNode, List<StepId> steps, WalkVisitor<NV,EV,N,E> visitor) {
		reduceWalk(fromNode, steps, null, (accumulator, walkedEdges, remainingSteps, n) -> {
			
			visitor.visitStep(walkedEdges, remainingSteps, n);
			
			return null;

		}).throwException();
	}

	@Override
	public <R> Result<R> reduceSteps(N fromNode, 
			                         List<StepId> steps, 
			                         R initialValue, 
			                         WalkReducer<NV,EV,N,E,R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);
		
		_reduceEdgeWalk(fromNode, steps, new ArrayList<>(), result, reducer);

		return result;
	}

	protected <R> void _reduceEdgeWalk(N fromNode, 
									   List<StepId> steps,
			                           List<Step<NV,EV,N,E>> walkedSteps,
			                           ResultNtro<R> result, 
			                           WalkReducer<NV,EV,N,E,R> reducer) {
		
		if(result.hasException()) {
			return;
		}
		
		if(steps.isEmpty()) {
			return;
		}
		
		StepId nextStep = steps.get(0);
		List<StepId> remainingSteps = ListUtils.subList(steps, 1);
		
		_reduceNextStepsById(fromNode, nextStep, result, (__, walkedStep) -> {

			try {
				
				List<Step<NV,EV,N,E>> newWalkedSteps = new ArrayList<>(walkedSteps);
				walkedSteps.add(walkedStep);

				result.registerValue(reducer.reduceStep(result.value(), newWalkedSteps, remainingSteps, walkedStep.to()));
				
				_reduceEdgeWalk(walkedStep.to(), steps, newWalkedSteps, result, reducer);

			}catch(Throwable t) {
				
				result.registerException(t);
				
			}
			
			return result.value();
		});
	}
	
	*/
	
	

}
