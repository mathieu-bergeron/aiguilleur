package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.SearchOptionsNtro;

public class WalkNtro<N extends Node<N,E,SO>,
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptionsNtro> 

       extends GenericWalkNtro<E>

       implements Walk<N,E,SO> {

	public WalkNtro() {
		super();
	}

	public WalkNtro(Walk<N, E, SO> walked) {
		super(walked);
	}

}
