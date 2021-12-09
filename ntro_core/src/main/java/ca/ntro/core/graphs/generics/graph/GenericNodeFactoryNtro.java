package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.initialization.Factory;

public class GenericNodeFactoryNtro<N extends GenericNode<N,E,SO>,
                                    E extends GenericEdge<N,E,SO>,
                                    SO extends SearchOptions> 


       implements GenericNodeFactory<N,E,SO> {
	
	private Class<N> nodeClass;

	public Class<N> getNodeClass() {
		return nodeClass;
	}

	public void setNodeClass(Class<N> nodeClass) {
		this.nodeClass = nodeClass;
	}

	@Override
	public N newInstance() {
		return Factory.newInstance(nodeClass);
	}


}
