package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class ReachableNodesStreamNtro<N extends GenericNode<N,E,SO>, 
                                      E extends GenericEdge<N,E,SO>,
                                      SO extends SearchOptions> 

       extends StreamNtro<VisitedNode<N,E,SO>>

	   implements ReachableNodesStream<N,E,SO> {
	
	private GenericNodeNtro<N,E,SO> node;
	private SO searchOptions;

	public GenericNodeNtro<N, E, SO> getNode() {
		return node;
	}

	public void setNode(GenericNodeNtro<N, E, SO> node) {
		this.node = node;
	}

	public SO getSearchOptions() {
		return searchOptions;
	}

	public ReachableNodesStreamNtro() {
	}

	public ReachableNodesStreamNtro(GenericNodeNtro<N, E, SO> genericNodeNtro, SO options) {
		setNode(genericNodeNtro);
		setSearchOptions(options);
	}

	public void setSearchOptions(SO searchOptions) {
		this.searchOptions = searchOptions;
	}

	@Override
	public void forEach_(Visitor<VisitedNode<N, E, SO>> visitor) throws Throwable {
		if(searchOptions.internal().searchStrategy() == SearchStrategy.DEPTH_FIRST_SEARCH) {

			node.visitReachableNodesDepthFirst(searchOptions, new WalkNtro<N,E,SO>(), visitor);

		}else {

			node.visitReachableNodesBreadthFirst(searchOptions, 
												 node.oneStepOptions(),
						                         new WalkNtro<N,E,SO>(), 
						                         visitor);
		}
	}

}
