package ca.ntro.core.graph_writer;

import ca.ntro.core.reflection.object_graph.ReferenceEdge;

public class RecordEdgeSpecNtro 

       extends EdgeSpecNtro
       
       implements RecordEdgeSpec {

	public RecordEdgeSpecNtro(RecordNodeSpecNtro fromSpec, 
			                  String fromPort, 
			                  ReferenceEdge edge, 
			                  RecordNodeSpecNtro toSpec, 
			                  String toPort) {

		super(fromSpec, fromPort, edge, toSpec, toPort);
	}

	@Override
	public String label() {
		return "";
	}

}
