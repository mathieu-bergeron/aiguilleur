package ca.ntro.core.graph_writer;

public interface NodeSpec extends GraphItemSpec {
	
	boolean isCluster();

	boolean isRecord();
	RecordSpec asRecord();
	
	String color();
	String shape();
}
