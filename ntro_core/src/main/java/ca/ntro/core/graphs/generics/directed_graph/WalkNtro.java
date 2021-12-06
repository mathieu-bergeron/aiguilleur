package ca.ntro.core.graphs.generics.directed_graph;

public class WalkNtro<N extends GenericNode<N,E,SO>,
                      E extends Edge<N,E,SO>,
                      SO extends SearchOptionsBuilder> 

       extends GenericWalkNtro<E>

       implements Walk<N,E,SO> {

	public WalkNtro() {
		super();
	}

	public WalkNtro(Walk<N, E, SO> walked) {
		super(walked);
	}

}
