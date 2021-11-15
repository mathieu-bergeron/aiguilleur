package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.writers.DagWriterNtro;
import ca.ntro.core.graphs.writers.ExternalGraphWriter;
import ca.ntro.core.identifyers.StorageId;

public class DagWriterMock extends DagWriterNtro<NodeMock, EdgeMock> {

	@Override
	protected ExternalGraphWriter<NodeMock, EdgeMock> createExeternalGraphWriter(StorageId storageId) {
		return new ExternalGraphWriterMock(storageId.toFilepath());
	}

}
