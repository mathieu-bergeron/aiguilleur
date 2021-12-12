package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.stream.Stream;

public interface GenericGraphStructure <N extends GenericNode<N,E,SO>, 
                                       E extends GenericEdge<N,E,SO>,
                                       SO extends SearchOptions> { 
	
	Stream<N> startNodes();
}
