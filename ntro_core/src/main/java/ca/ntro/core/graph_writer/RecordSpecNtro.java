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
	
	
	
	public RecordSpecNtro() {
		super();
	}

	public RecordSpecNtro(String portName) {
		super(portName);
	}
	
	public RecordSpecNtro(String portName, RecordSpecNtro recordSpecNtro) {
		super(portName, recordSpecNtro);
	}
	
	
	


	@Override
	public boolean isRecord() {
		return true;
	}
	
	@Override
	public Stream<RecordItemSpec> items() {
		return new StreamNtro<RecordItemSpec>() {
			@Override
			public void _forEach(Visitor<RecordItemSpec> visitor) throws Throwable {
				for(RecordItemSpec item : getItems()) {
					visitor.visit(item);
				}
			}
		};
	}

	public RecordSpecNtro addSubRecord(String attributeName) {
		RecordSpecNtro subRecord = new RecordSpecNtro(attributeName, this);
		
		getItems().add(subRecord);
		
		return subRecord;
	}

	public RecordItemSpecNtro addItem(String attributeName) {
		RecordItemSpecNtro newItem = new RecordItemSpecNtro(attributeName, this);
		
		getItems().add(newItem);
		
		return newItem;
	}

	public RecordItemSpecNtro addItem(String attributeName, String value) {
		RecordItemSpecNtro newItem = new RecordItemSpecNtro(attributeName, value, this);
		
		getItems().add(newItem);
		
		return newItem;
	}
}
