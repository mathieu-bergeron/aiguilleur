package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface DirectedGraphBuilder<NV extends NodeValue, 
                                      EV extends EdgeValue,
                                      N extends Node<NV>,
                                      E extends Edge<EV>> 

       extends   GenericGraphBuilder<NV,EV,N,E,DirectedGraphStructure<NV,EV,N,E>, 
                 DirectedGraph<NV,EV,N,E>> {

	public static <NV extends NodeValue, 
	               EV extends EdgeValue,
	               N extends Node<NV>,
	               E extends Edge<EV>> 
	
	       DirectedGraphBuilder<NV,EV,N,E> 

		   newBuilder(){

		return new DirectedGraphNtro<NV,EV,N,E>();
	}

	public static <NV extends NodeValue, 
	               EV extends EdgeValue,
	               N extends Node<NV>,
	               E extends Edge<EV>> 
	
      	    DirectedGraphBuilder<NV,EV,N,E> newBuilder(String graphName){

		return new DirectedGraphNtro<NV,EV,N,E>(graphName);
	}

}
