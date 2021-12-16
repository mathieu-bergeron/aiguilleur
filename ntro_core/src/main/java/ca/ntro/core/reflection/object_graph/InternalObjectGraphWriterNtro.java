package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graph_writer.NodeSpecNtro;
import ca.ntro.core.graph_writer.RecordNodeSpecNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericInternalDirectedGraphWriterNtro;
import ca.ntro.core.graphs.generics.graph.GenericGraph;

public class InternalObjectGraphWriterNtro       

       extends   GenericInternalDirectedGraphWriterNtro<ObjectNode, 
                                                       ReferenceEdge, 
                                                       ObjectGraphSearchOptions,
                                                       ObjectGraphWriterOptions> 

      implements InternalObjectGraphWriter {

	@Override
	protected void adjustNodeSpecAttributes(ObjectNode node, 
			                                ObjectGraphWriterOptions options,
			                                NodeSpecNtro nodeSpec) {
		
		if(!options.objectAsStructure()) {
			super.adjustNodeSpecAttributes(node, options, nodeSpec);
		}
	}
	
	@Override
	protected void writeNodes(GenericGraph<ObjectNode,ReferenceEdge,ObjectGraphSearchOptions,ObjectGraphWriterOptions> graph, 
			                  ObjectGraphWriterOptions options,
			                  GraphWriter writer) {
		
		if(!options.objectAsStructure()) {

			super.writeNodes(graph, options, writer);

		}else {
			
			ObjectStructureWriter objectStructureWriter = new ObjectStructureWriter(graph, this, options, writer);

			objectStructureWriter.writeNodes();
			objectStructureWriter.writeEdges();
		}
		
	}

	protected RecordNodeSpecNtro recordNodeSpec(ObjectNode node, ObjectGraphWriterOptions options) {
		RecordNodeSpecNtro recordSpec = new RecordNodeSpecNtro(node);
		
		adjustNodeSpecAttributes(node, options, recordSpec);
		
		return recordSpec;
	}

	@Override
	protected void writeEdges(GenericGraph<ObjectNode,ReferenceEdge,ObjectGraphSearchOptions,ObjectGraphWriterOptions> graph, 
			                  ObjectGraphWriterOptions options,
			                  GraphWriter writer) {
		
		if(!options.objectAsStructure()) {
			super.writeEdges(graph, options, writer);
		}
	}
}
