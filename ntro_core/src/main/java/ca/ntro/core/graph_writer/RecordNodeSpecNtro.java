package ca.ntro.core.graph_writer;


import ca.ntro.core.graphs.generics.graph.GenericNode;

public class RecordNodeSpecNtro 
 
       extends NodeSpecNtro 
       
       implements RecordNodeSpec {
	

	private RecordSpecNtro record = new RecordSpecNtro();

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
	public RecordSpec record() {
		return getRecord();
	}

	@Override
	public String shape() {
		return "record";
	}

	@Override
	public String label() {
		return labelFromRecordSpec(getRecord());
	}

	private String labelFromRecordSpec(RecordSpec recordSpec) {
		StringBuilder builder = new StringBuilder();

		recordSpec.items().forEach(item -> {
			if(builder.length() > 0) {
				builder.append("|");
			}

			if(item.isRecord()) {
				builder.append('{');
				builder.append(labelFromRecordSpec(item.asRecord()));
				builder.append('}');

			}else {
				if(item.hasPort()) {
					builder.append('<');
					builder.append(item.port());
					builder.append('>');
				}
				
				if(item.hasValue()) {
					builder.append(item.value());
				}
			}
		});

		return builder.toString();
	}
}
