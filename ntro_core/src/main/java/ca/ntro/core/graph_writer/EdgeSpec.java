package ca.ntro.core.graph_writer;

public interface EdgeSpec extends GraphItemSpec {
	
	NodeSpec from();
	
	boolean hasFromPort();
	String fromPort();

	NodeSpec to();
	
	boolean hasToPort();
	String toPort();

}
