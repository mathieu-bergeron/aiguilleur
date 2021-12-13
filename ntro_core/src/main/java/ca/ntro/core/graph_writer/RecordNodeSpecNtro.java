package ca.ntro.core.graph_writer;


import ca.ntro.core.graphs.generics.graph.GenericNode;

public class RecordNodeSpecNtro 
 
       extends NodeSpecNtro 
       
       implements RecordNodeSpec {
	

	private RecordSpecNtro record = new RecordSpecNtro("");

	public RecordSpecNtro getRecord() {
		return record;
	}

	public void setRecord(RecordSpecNtro record) {
		this.record = record;
	}

	public RecordNodeSpecNtro() {
		super();
	}

	public RecordNodeSpecNtro(GenericNode<?, ?, ?> node) {
		super(node);
	}

	@Override
	public boolean isRecordNode() {
		return true;
	}

	@Override
	public RecordSpec record() {
		return getRecord();
	}
}
