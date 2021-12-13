package ca.ntro.core.graph_writer;

public interface NodeSpec extends GraphItemSpec {
	
	boolean isCluster();

	boolean isRecordNode();
	RecordNodeSpec asRecordNode();
	
	String color();
	String shape();
}
