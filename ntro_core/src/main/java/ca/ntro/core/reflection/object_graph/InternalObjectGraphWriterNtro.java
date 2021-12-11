package ca.ntro.core.reflection.object_graph;

import java.util.HashSet;
import java.util.Set;

import ca.ntro.core.graphs.generics.directed_graph.GenericInternalDirectedGraphWriterNtro;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.GraphWriterException;
import ca.ntro.core.graphs.graph_writer.NodeSpecNtro;
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
		
		Set<String> writtenNodes = new HashSet<>();

		graph.forEachNode(n -> {
			try {
				
				if(!writtenNodes.contains(n.id().toKey().toString())) {
					writtenNodes.add(n.id().toKey().toString());

					NodeSpecNtro nodeSpec = nodeSpec(n, options);
					
					if(options.objectAsStructure()) {
						

						n.forEachEdge(e -> {
							
							if(e.to().isSimpleValue()) {
								
								//nodeSpec.addSimpleAttribute(e.name().toString(), e.to().asSimpleValue().asString());

							}else if(e.to().isList()){
								
							}else if(e.to().isMap()){
								
							}
						});

						throw new RuntimeException("TODO");
						
						
					}else {

						writer.addNode(nodeSpec);
					}
				}

			} catch (GraphWriterException e) {

				Ntro.exceptionThrower().throwException(e);
			}
		});
	}

}
