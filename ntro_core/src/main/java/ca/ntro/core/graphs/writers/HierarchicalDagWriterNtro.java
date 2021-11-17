package ca.ntro.core.graphs.writers;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDag;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalNode;
import ca.ntro.core.identifyers.StorageId;

public abstract class HierarchicalDagWriterNtro<NV extends NodeValue, EV extends EdgeValue> extends DagWriterNtro<NV,EV> implements HierarchicalDagWriter<NV,EV> {

	@Override
	protected abstract ExternalHierarchicalGraphWriter<NV, EV> createExeternalGraphWriter(StorageId storageId);

	@Override
	public void write(HierarchicalDag<NV, EV> hierarchicalDag) {

	}

}
