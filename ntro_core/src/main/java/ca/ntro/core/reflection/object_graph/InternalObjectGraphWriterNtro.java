package ca.ntro.core.reflection.object_graph;


import ca.ntro.core.graphs.generics.directed_graph.GenericInternalDirectedGraphWriterNtro;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.GraphWriterException;
import ca.ntro.core.graphs.graph_writer.StructureSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class InternalObjectGraphWriterNtro       

       extends   GenericInternalDirectedGraphWriterNtro<ObjectNode, 
                                                       ReferenceEdge, 
                                                       ObjectGraphSearchOptions,
                                                       ObjectGraphWriterOptions> 

      implements InternalObjectGraphWriter {

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

	protected StructureSpecNtro structureSpec(ObjectNode node, ObjectGraphWriterOptions options) {
		StructureSpecNtro structureSpec = new StructureSpecNtro(node);
		
		adjustNodeSpecAttributes(node, options, structureSpec);
		
		return structureSpec;
	}

	protected void writeNodeAsStructure(ObjectNode node,
						                ObjectGraphSearchOptions oneStepOptions,
			                            ObjectGraphWriterOptions options,
			                            GraphWriter writer) throws GraphWriterException {

			StructureSpecNtro nodeSpec = structureSpec(node, options);
			/*
			
			node.forEachEdge(e -> {
				
				if(e.to().isSimpleValue()) {
					
					nodeSpec.addAttribute(e.name().toString(), e.to().asSimpleValue().asString());

				}else if(e.to().isList()){
					
					e.to().forEachEdge(ee -> {
						
						String portName = nodeSpec.addToList(e.name().toString(), e.to());
						
						if(!ee.to().isSimpleValue()) {
							
							// TODO: how to specify port
							writer.addEdge(nodeSpec, new EdgeSpecNtro(e), new NodeSpecNtro(ee.to()));
						}
					});
					

					
				}else if(e.to().isMap()){
					
					nodeSpec.addToMap(e.name().toString(), e.to());
				}
			});*/

			writer.addNode(nodeSpec);
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
