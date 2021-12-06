package ca.ntro.core.graphs.generics.directed_graph;

public class VisitedEdgeNtro<N extends GenericNode<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> 

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

	public VisitedEdgeNtro(Walk<N,E,SO> walked, E edge) {
		super(walked);
		setEdge(edge);
	}

	@Override
	public E edge() {
		return getEdge();
	}
}
