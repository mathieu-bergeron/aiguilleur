package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.graph_writer.EdgeSpec;
import ca.ntro.core.graphs.graph_writer.StructureSpec;

public interface StructureEdgeSpec 


       extends EdgeSpec {
	
	StructureSpec from();
	StructureSpec to();

}
