package ca.ntro.core.graph_writer;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class RecordSpecNtro 
 
       extends NodeSpecNtro 
       
       implements RecordSpec {

	private String port;
	private String value;
	private List<RecordItemSpec> items = new ArrayList<>();

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<RecordItemSpec> getItems() {
		return items;
	}

	public void setItems(List<RecordItemSpec> items) {
		this.items = items;
	}

	public RecordSpecNtro(GenericNode<?, ?, ?> node) {
		super(node);
	}

	@Override
	public boolean isRecord() {
		return true;
	}
	
	@Override
	public boolean hasPort() {
		return getPort() != null;
	}

	@Override
	public String port() {
		return getPort();
	}

	@Override
	public boolean hasValue() {
		return getValue() != null;
	}

	@Override
	public String value() {
		return getValue();
	}

	@Override
	public Stream<RecordItemSpec> items() {
		return new StreamNtro<RecordItemSpec>() {
			@Override
			public void _forEach(Visitor<RecordItemSpec> visitor) throws Throwable {
				for(RecordItemSpec item : items) {
					visitor.visit(item);
				}
			}
		};
	}

}
