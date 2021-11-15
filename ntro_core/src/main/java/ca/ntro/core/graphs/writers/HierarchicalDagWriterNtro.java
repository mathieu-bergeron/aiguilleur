package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.generic_graph.Edge;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalNode;
import ca.ntro.core.identifyers.StorageId;

public abstract class HierarchicalDagWriterNtro<HN extends HierarchicalNode, E extends Edge> extends DagWriterNtro<HN,E> implements HierarchicalDagWriter<HN,E> {

	@Override
	protected abstract ExternalHierarchicalGraphWriter<HN, E> createExeternalGraphWriter(StorageId storageId);

	@Override
	public void write(HierarchicalDag<HN, E> hierarchicalDag) {

	}

}
