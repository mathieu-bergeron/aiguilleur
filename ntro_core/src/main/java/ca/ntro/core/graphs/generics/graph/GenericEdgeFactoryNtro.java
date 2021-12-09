package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.initialization.Factory;

public class GenericEdgeFactoryNtro<N extends GenericNode<N,E,SO>,
                                    E extends GenericEdge<N,E,SO>,
                                    SO extends SearchOptions> 


       implements GenericEdgeFactory<N,E,SO> {
	
	
	private Class<E> edgeClass;

	public Class<E> getEdgeClass() {
		return edgeClass;
	}

	public void setEdgeClass(Class<E> edgeClass) {
		this.edgeClass = edgeClass;
	}

	@Override
	public E newInstance() {
		return Factory.newInstance(getEdgeClass());
	} 
}
