package ca.ntro.core.reflection.object_graph;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ntro.core.graph_writer.EdgeSpec;
import ca.ntro.core.graph_writer.EdgeSpecNtro;
import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graph_writer.GraphWriterException;
import ca.ntro.core.graph_writer.NodeSpecNtro;
import ca.ntro.core.graph_writer.RecordItemSpecNtro;
import ca.ntro.core.graph_writer.RecordNodeSpec;
import ca.ntro.core.graph_writer.RecordNodeSpecNtro;
import ca.ntro.core.graph_writer.RecordSpecNtro;
import ca.ntro.core.graphs.generics.directed_graph.GenericInternalDirectedGraphWriterNtro;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.initialization.Ntro;

public class InternalObjectGraphWriterNtro       

       extends   GenericInternalDirectedGraphWriterNtro<ObjectNode, 
                                                       ReferenceEdge, 
                                                       ObjectGraphSearchOptions,
                                                       ObjectGraphWriterOptions> 

      implements InternalObjectGraphWriter {
	
	private List<EdgeSpec> recordEdges = new ArrayList<>();
	
	@Override
	protected void writeNodes(GenericGraph<ObjectNode,ReferenceEdge,ObjectGraphSearchOptions,ObjectGraphWriterOptions> graph, 
			                  ObjectGraphWriterOptions options,
			                  GraphWriter writer) {
		
		if(!options.objectAsStructure()) {

			super.writeNodes(graph, options, writer);

		}else {
			
			Set<String> writtenNodes = new HashSet<>();

			graph.startNodes().forEach(n -> {
				
				try {

					writeNodeAsStructure(writtenNodes, n, options, writer);

				} catch (GraphWriterException e) {

					Ntro.exceptionThrower().throwException(e);
				}
			});
		}
		
	}

	protected RecordNodeSpecNtro recordNodeSpec(ObjectNode node, ObjectGraphWriterOptions options) {
		RecordNodeSpecNtro recordSpec = new RecordNodeSpecNtro(node);
		
		adjustNodeSpecAttributes(node, options, recordSpec);
		
		return recordSpec;
	}
	
	protected void writeNodeAsStructure(Set<String> writtenNodes,
			                            ObjectNode node,
			                            ObjectGraphWriterOptions options,
			                            GraphWriter writer) throws GraphWriterException {
		
		if(!writtenNodes.contains(node.id().toKey().toString())) {
			writtenNodes.add(node.id().toKey().toString());

			RecordNodeSpecNtro fromSpec = recordNodeSpec(node, options);
			RecordSpecNtro mainRecord = fromSpec.getRecord();
			
			mainRecord.addItem(new RecordItemSpecNtro(RecordNodeSpec.MAIN_PORT_NAME, node.label()));
			
			writeNodeAsStructure(writtenNodes, fromSpec, node, mainRecord, options, writer);

			writer.addNode(fromSpec);
		}
	}

	protected void writeNodeAsStructure(Set<String> writtenNodes,
			                            RecordNodeSpecNtro fromSpec,
										ObjectNode currentNode,
			                            RecordSpecNtro currentRecord,
			                            ObjectGraphWriterOptions options,
			                            GraphWriter writer) throws GraphWriterException {

			currentNode.edges().forEach(e -> {
				
				String attributeName = e.name().toString();
				
				if(e.to().isSimpleValue()) {
					
					currentRecord.addItem(attributeName, e.to().asSimpleValue().asString());

				}else if(e.to().isList() 
						|| e.to().isMap()){
					
					RecordSpecNtro subRecord = currentRecord.addSubRecord(attributeName);
					
					e.to().edges().forEach(ee -> {

						writeNodeAsStructure(writtenNodes, fromSpec, ee.to(), subRecord, options, writer);

					});
					
				}else if(e.to().isUserDefinedObject()){

					RecordItemSpecNtro item = currentRecord.addItem(attributeName);
					
					recordEdges.add(new EdgeSpecNtro(fromSpec, 
							                         item.port(),
							                         e,
							                         nodeSpec(e.to(), options),
							                         RecordNodeSpec.MAIN_PORT_NAME));

					writeNodeAsStructure(writtenNodes, e.to(), options, writer);
				}
			});
	}
	

	@Override
	protected void writeEdges(GenericGraph<ObjectNode,ReferenceEdge,ObjectGraphSearchOptions,ObjectGraphWriterOptions> graph, 
			                  ObjectGraphWriterOptions options,
			                  GraphWriter writer) {
		
		if(!options.objectAsStructure()) {

			super.writeEdges(graph, options, writer);

		}else {
			
			for(EdgeSpec edge : recordEdges) {
				
				try {
				
					writer.addEdge(edge);

				}catch(GraphWriterException e) {
					
					Ntro.exceptionThrower().throwException(e);
				}
			}
		}
	}
}
