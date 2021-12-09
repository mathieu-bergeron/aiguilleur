package ca.ntro.core.graphs.generics.graph;

public class VisitedNodeNtro <N extends GenericNode<N,E,SO>, 
                              E extends GenericEdge<N,E,SO>,
                              SO extends SearchOptions> 

       extends VisitedItemNtro<N,E,SO>

       implements VisitedNode<N,E,SO> {
	
	private N node;

	public N getNode() {
		return node;
	}

	public void setNode(N node) {
		this.node = node;
	}
	
	public VisitedNodeNtro() {
	}
	
	public VisitedNodeNtro(Walk<N,E,SO> walked, N node) {
		super(walked);
		setNode(node);
	}

	@Override
	public N node() {
		return getNode();
	}
}