package ca.ntro.core.graphs.tests;

import ca.ntro.core.graphs.writers.DagWriterNtro;
import ca.ntro.core.graphs.writers.ExternalGraphWriter;
import ca.ntro.core.identifyers.StorageId;

public class DagWriterMock extends DagWriterNtro<MockNodeValue, MockEdgeValue> {

	@Override
	protected ExternalGraphWriter<MockNodeValue, MockEdgeValue> createExeternalGraphWriter(StorageId storageId) {
		return new ExternalGraphWriterMock(storageId.toFilepath());
	}

}
