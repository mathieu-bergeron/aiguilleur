package ca.ntro.core.graphs.graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface GraphBuilder<NV extends NodeValue, 
                              EV extends EdgeValue,
                              N extends Node<NV>,
                              E extends Edge<EV>> extends GenericGraphBuilder<NV,EV,N,E,GraphStructure<NV,EV,N,E>, Graph<NV,EV,N,E>> {
	
	public static <NV extends NodeValue, 
	               EV extends EdgeValue,
	               N extends Node<NV>,
	               E extends Edge<EV>> GraphBuilder<NV,EV,N,E> newBuilder(){

		return new GraphBuilderNtro<NV,EV,N,E>();
	}

	public static <NV extends NodeValue, 
	               EV extends EdgeValue,
	               N extends Node<NV>,
	               E extends Edge<EV>> GraphBuilder<NV,EV,N,E> newBuilder(String graphName){

		return new GraphBuilderNtro<NV,EV,N,E>(graphName);
	}

}
