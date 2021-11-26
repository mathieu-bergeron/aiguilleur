package ca.ntro.core.graphs.writers;

public interface GraphWriterOptions {
	
	boolean isDirected();
	
	public static GraphWriterOptions directed(boolean directed) {
		GraphWriterOptionsNtro graphWriterOptions = new GraphWriterOptionsNtro();
		
		graphWriterOptions.setDirected(directed);
		
		return graphWriterOptions;
	}
	

}
