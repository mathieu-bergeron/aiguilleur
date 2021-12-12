package ca.ntro.core.graphs.generics.graph;

public class VisitedEdgeNtro<N extends GenericNode<N,E,SO>, 
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions> 

       extends VisitedItemNtro<N,E,SO>

       implements VisitedEdge<N,E,SO> {
	
	private E Edge;

	public E getEdge() {
		return Edge;
	}

	public void setEdge(E edge) {
		Edge = edge;
	}
	
	public VisitedEdgeNtro() {
	}

	public VisitedEdgeNtro(WalkNtro<N,E,SO> walked, E edge) {
		super(walked);
		setEdge(edge);
	}

	@Override
	public E edge() {
		return getEdge();
	}
}
