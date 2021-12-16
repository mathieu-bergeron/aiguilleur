package ca.ntro.core.graph_writer;

public interface NodeSpec extends GraphItemSpec {
	
	boolean isCluster();
	
	String color();
	String shape();
}
