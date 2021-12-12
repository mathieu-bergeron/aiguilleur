package ca.ntro.core.graphs.generics.graph;

public class VisitedNodeNtro <N extends GenericNode<N,E,SO>, 
                              E extends GenericEdge<N,E,SO>,
                              SO extends SearchOptions> 

       extends VisitedItemNtro<N,E,SO>

       implements VisitedNode<N,E,SO> {
	
	private GenericNodeNtro<N,E,SO> node;

	public GenericNodeNtro<N,E,SO> getNode() {
		return node;
	}

	public void setNode(GenericNodeNtro<N,E,SO> node) {
		this.node = node;
	}
	
	public VisitedNodeNtro() {
	}
	
	public VisitedNodeNtro(WalkNtro<N,E,SO> walked, GenericNodeNtro<N,E,SO> node) {
		super(walked);
		setNode(node);
	}

	@SuppressWarnings("unchecked")
	@Override
	public N node() {
		return (N) getNode();
	}
}
