package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilder;

public interface HierarchicalGraphBuilder<NV extends NodeValue, 
                                          EV extends EdgeValue,
                                          N extends HierarchicalNode<NV>,
                                          E extends Edge<EV>> 

       extends   GenericGraphBuilder<NV,EV,N,E,HierarchicalGraphStructure<NV,EV,N,E>, HierarchicalGraph<NV,EV,N,E>> {

	void addSubNode(Node<NV> parentNode, Node<NV> subNode);

	public static <NV extends NodeValue, 
	               EV extends EdgeValue,
	               N extends HierarchicalNode<NV>,
	               E extends Edge<EV>> HierarchicalGraphBuilder<NV,EV,N,E> newBuilder(){

		return new HierarchicalGraphBuilderNtro<NV,EV,N,E>();
	}

	public static <NV extends NodeValue, 
	               EV extends EdgeValue,
	               N extends HierarchicalNode<NV>,
	               E extends Edge<EV>> HierarchicalGraphBuilder<NV,EV,N,E> newBuilder(String graphName){

		return new HierarchicalGraphBuilderNtro<NV,EV,N,E>(graphName);
	}

}
