package ca.ntro.core.reflection.object_graph;


import java.util.ArrayList;
import java.util.List;

import ca.ntro.core.graph_writer.EdgeSpec;
import ca.ntro.core.graph_writer.EdgeSpecNtro;
import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graph_writer.GraphWriterException;
import ca.ntro.core.graph_writer.NodeSpecNtro;
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

			ObjectGraphSearchOptions oneStepOptions = new ObjectGraphSearchOptionsNtro();
			oneStepOptions.setMaxDistance(1);
			
			graph.startNodes().forEach(n -> {
				
				try {

					writeNodeAsStructure(n, oneStepOptions, options, writer);

				} catch (GraphWriterException e) {

					Ntro.exceptionThrower().throwException(e);
				}
			});
		}
		
	}

	protected RecordSpecNtro recordSpec(ObjectNode node, ObjectGraphWriterOptions options) {
		RecordSpecNtro recordSpec = new RecordSpecNtro(node);
		
		adjustNodeSpecAttributes(node, options, recordSpec);
		
		return recordSpec;
	}

	protected void writeNodeAsStructure(ObjectNode node,
						                ObjectGraphSearchOptions oneStepOptions,
			                            ObjectGraphWriterOptions options,
			                            GraphWriter writer) throws GraphWriterException {

			RecordSpecNtro fromSpec = recordSpec(node, options);

			node.edges().forEach(e -> {
				
				String attributeName = e.name().toString();
				
				if(e.to().isSimpleValue()) {
					
					//fromSpec.addAttribute(attributeName, e.to().asSimpleValue().asString());

				}else if(e.to().isList()){
					
					e.to().edges().forEach(ee -> {
						
						String indexName = ee.name().toString();
						String portName = attributeName + "_" + indexName;
						
						//fromSpec.addToList(e.name().toString(), e.to());
						
						if(!ee.to().isSimpleValue()) {
							
							// TODO: how to specify port
							//writer.addEdge(fromSpec, new EdgeSpecNtro(e), new NodeSpecNtro(ee.to()));
						}
					});

					
				}else if(e.to().isMap()){
					
					//fromSpec.addToMap(e.name().toString(), e.to());
				}
			});

			writer.addNode(fromSpec);
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
