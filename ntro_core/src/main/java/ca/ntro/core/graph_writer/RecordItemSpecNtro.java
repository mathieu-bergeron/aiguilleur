package ca.ntro.core.graph_writer;

public class RecordItemSpecNtro implements RecordItemSpec {
	
	private String portName;
	private String value;
	private RecordSpecNtro parent;
	

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public RecordSpecNtro getParent() {
		return parent;
	}

	public void setParent(RecordSpecNtro parent) {
		this.parent = parent;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public RecordItemSpecNtro() {
	}
	
	public RecordItemSpecNtro(String portName) {
		setPortName(portName);
	}

	public RecordItemSpecNtro(String portName, String value) {
		setPortName(portName);
		setValue(value);
	}

	public RecordItemSpecNtro(String portNAme, RecordSpecNtro parent) {
		setPortName(portNAme);
		setParent(parent);
	}

	public RecordItemSpecNtro(String portName, String value, RecordSpecNtro parent) {
		setPortName(portName);
		setValue(value);
		setParent(parent);
	}

	public boolean hasParent() {
		return getParent() != null;
	}
	
	public RecordSpecNtro parent() {
		return getParent();
	}

	@Override
	public boolean hasPort() {
		return getPortName() != null;
	}

	@Override
	public String port() {
		String port;
		
		if(hasParent()) {

			port = parent().port() + "_" + getPortName();

		}else {
			
			port = getPortName();
			
		}
		
		return port;
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
	public boolean isRecord() {
		return false;
	}

	@Override
	public RecordSpec asRecord() {
		return (RecordSpec) this;
	}
}
