package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.GenericGraphBuilderNtro;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriter;

public class      HierarchicalGraphBuilderNtro<NV extends NodeValue, 
                                               EV extends EdgeValue,
                                               N extends HierarchicalNode<NV,EV,N,E>,
                                               E extends Edge<EV>> 

       extends    GenericGraphBuilderNtro<NV,EV,N,E,HierarchicalGraphStructure<NV,EV,N,E>,HierarchicalGraph<NV,EV,N,E>> 

       implements HierarchicalGraph<NV,EV,N,E>, HierarchicalGraphBuilder<NV,EV,N,E> {

	public HierarchicalGraphBuilderNtro() {
		super();
	}

	public HierarchicalGraphBuilderNtro(String graphName) {
		super(graphName);
	}

	@Override
	protected InternalGraphWriter<NV, EV, N, E> internalGraphWriter() {
		return new InternalHierarchicalGraphWriterNtro<>();
	}

	@Override
	protected SearchOptions defaultSearchOptions() {
		return new HierarchicalGraphSearchOptions();
	}

	@Override
	protected HierarchicalGraphStructure<NV, EV, N, E> createGraphStructure() {
		return new HierarchicalGraphStructureNtro<>();
	}

	@Override
	protected void detectCycleFrom(N from) {

	}

	@Override
	public void addSubNode(Node<NV> parentNode, Node<NV> subNode) {
	}


}
