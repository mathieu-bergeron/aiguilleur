package ca.ntro.core.graph_writer;

public interface RecordItemSpec {
	
	boolean hasPort();
	String port();
	
	boolean hasValue();
	String value();

	boolean isRecord();
	RecordSpec asRecord();
}
