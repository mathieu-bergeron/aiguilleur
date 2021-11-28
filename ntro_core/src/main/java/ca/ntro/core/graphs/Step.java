package ca.ntro.core.graphs;

import ca.ntro.core.graphs.generic_graph.GenericStep;

public interface Step<NV extends NodeValue, 
                            EV extends EdgeValue,
                            N extends Node<NV>,
                            E extends Edge<EV>> 

       extends GenericStep {

	Direction direction();
	
	N from();
	E edge();
	N to();

}
