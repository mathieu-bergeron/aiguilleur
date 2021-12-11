package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.directed_graph.DirectedGraphWriterOptions;

public interface ObjectGraphWriterOptions extends DirectedGraphWriterOptions {
	
	boolean objectAsStructure();
	boolean stringAsSimpleValue();
	boolean mapAsSimpleValue();
	boolean listAsSimpleValue();
	

}
