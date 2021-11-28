package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.Step;

public class WalkNtro<NV extends NodeValue, 
                      EV extends EdgeValue,
                      N extends Node<NV>,
                      E extends Edge<EV>> 

	   extends GenericWalkNtro<Step<NV,EV,N,E>>
 
       implements Walk<NV,EV,N,E> {
	

}
