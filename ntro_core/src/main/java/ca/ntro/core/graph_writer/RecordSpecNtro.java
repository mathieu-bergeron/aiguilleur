package ca.ntro.core.graph_writer;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

public class      RecordSpecNtro 

       extends    RecordItemSpecNtro

       implements RecordSpec {

	private List<RecordItemSpec> items = new ArrayList<>();

	public List<RecordItemSpec> getItems() {
		return items;
	}

	public void setItems(List<RecordItemSpec> items) {
		this.items = items;
	}

	public void addItem(RecordItemSpec item) {
		getItems().add(item);
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
