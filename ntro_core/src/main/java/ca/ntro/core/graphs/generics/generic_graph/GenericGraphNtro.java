package ca.ntro.core.graphs.generics.generic_graph;


import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream._Reducer;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class GenericGraphNtro<N extends Node<N,E,SO>, 
                                       E extends Edge<N,E,SO>,
                                       SO extends SearchOptionsBuilder> 

       implements     GenericGraph<N,E,SO> {
	
	private GraphId id;

	public GraphId getId() {
		return id;
	}

	public void setId(GraphId id) {
		this.id = id;
	}

	@Override
	public GraphId id() {
		return getId();
	}

	@Override
	public String label() {
		return getId().toKey().toString();
	}

	@Override
	public void write(GraphWriter writer) {
		internalGraphWriter().write(this, writer);
	}

	public abstract InternalGraphWriter<N,E,SO> internalGraphWriter();
	public abstract GenericGraphStructure<N,E,SO> graphStructure();

	@Override
	public abstract SO defaultSearchOptions();


	@Override
	public N findNode(String nodeId) {
		return findNode(new NodeIdNtro(nodeId));
	}

	@Override
	public N findNode(NodeId nodeId) {
		Result<N> result = reduceNodes(null, (accumulator, n) -> {
			if(accumulator != null) {
				throw new Break();
			}

			if(n.id().equals(nodeId)) {
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
		
		graphStructure().reduceStartNodes(result, reducer);

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
				return;
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
					return;
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
					return;
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


	public Stream<N> startNodes(){
		return new StreamNtro<N>(){
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<N, R> _reducer) {

				// JSweet: we need explicit variables to avoid typing errors
				GenericGraphStructure<N,E,SO> graphStructure = graphStructure();
				graphStructure._reduceStartNodes(result, _reducer);
			}
		};
	}

	public Stream<N> nodes(){
		return new StreamNtro<N>(){
			@Override
			public <R> void _reduce(ResultNtro<R> result, _Reducer<N, R> _reducer) {

				Stream<N> startNodes = startNodes();
				Stream<String> startNodeKeys = startNodes.map(sn -> sn.id().toKey().toString());

				SO options = defaultSearchOptions();
				
				options.internal()
				       .visitedNodes()
				       .addAll(startNodeKeys.collect());
				
				startNodes._reduce(result, _reducer);
				
				startNodes.forEach(startNode -> {

					Stream<VisitedNode<N,E,SO>> reachedNodes = startNode.reachableNodes(options);
					
					Stream<N> nodes = reachedNodes.map(rn -> rn.node());
					
					nodes._reduce(result, _reducer);
				});
			}
		};
	}

	public Stream<E> edges(){
		return null;
	}

}
