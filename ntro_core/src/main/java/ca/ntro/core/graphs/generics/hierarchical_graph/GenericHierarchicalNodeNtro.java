package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.common.NodeId;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericNodeNtro;
import ca.ntro.core.graphs.generics.graph.VisitedNode;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;

public abstract class GenericHierarchicalNodeNtro<N extends GenericHierarchicalNode<N,E,SO>,
 									              E extends GenericEdge<N,E,SO>,
 									              SO extends HierarchicalSearchOptions>

       extends        GenericNodeNtro<N,E,SO> 

	   implements     GenericHierarchicalNode<N,E,SO> {

	public GenericHierarchicalNodeNtro() {
	}

	public GenericHierarchicalNodeNtro(NodeId id) {
		super(id);
	}

	@Override
	public boolean hasSubNodes() {
		return !subNodes().isEmpty();
	}

	@Override
	public boolean hasParent() {
		return !parentNodes().isEmpty();
	}

	@Override
	public N parent() {
		return parentNodes().findFirst(pn -> true).node();
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> subNodes(){
		return subNodes(defaultSearchOptions());
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> subNodes(SO options){
		return reachableNodes(subNodeOptions(options));
	}

	protected SO subNodeOptions(SO options) {

		InternalSearchOptionsNtro subNodeOptions = new InternalSearchOptionsNtro();
		subNodeOptions.copyOptions(options.internal());
		subNodeOptions.setDirections(new Direction[] {Direction.DOWN});
		
		options.copyOptions(subNodeOptions);
		
		return options;
	}
	
	@Override
	public Stream<VisitedNode<N,E,SO>> parentNodes(){
		return parentNodes(defaultSearchOptions());
	}

	@Override
	public Stream<VisitedNode<N,E,SO>> parentNodes(SO options){
		return reachableNodes(parentNodeOptions(options));
	}

	protected SO parentNodeOptions(SO options) {

		InternalSearchOptionsNtro parentNodeOptions = new InternalSearchOptionsNtro();
		parentNodeOptions.copyOptions(options.internal());
		parentNodeOptions.setDirections(new Direction[] {Direction.UP});
		
		options.copyOptions(parentNodeOptions);
		
		return options;
	}
	
}
