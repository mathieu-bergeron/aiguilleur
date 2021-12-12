package ca.ntro.core.graphs.generics.graph;

public class WalkNtro<N extends GenericNode<N,E,SO>,
                      E extends GenericEdge<N,E,SO>,
                      SO extends SearchOptions> 

       extends GenericWalkNtro<E,Walk<N,E,SO>>

       implements Walk<N,E,SO> {

	public WalkNtro() {
		super();
	}

	public WalkNtro(Walk<N, E, SO> walked) {
		super(walked);
	}

}
