package ca.ntro.core.graphs.graph_writer;

import ca.ntro.core.graph_writer.NodeSpec;
import ca.ntro.core.graph_writer.RecordNodeSpec;

public class MockNodeSpec implements NodeSpec {

	@Override
	public String id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCluster() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRecordNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RecordNodeSpec asRecordNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String color() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String shape() {
		// TODO Auto-generated method stub
		return null;
	}

}
