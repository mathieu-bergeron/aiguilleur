package ca.ntro.core.graphs.graph_writer;

public interface GraphWriterOptions {
	
	boolean isDirected();
	
	public static GraphWriterOptions directed(boolean directed) {
		GraphWriterOptionsNtro graphWriterOptions = new GraphWriterOptionsNtro();
		
		graphWriterOptions.setDirected(directed);
		
		return graphWriterOptions;
	}
	

}
