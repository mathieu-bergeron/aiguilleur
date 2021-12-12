package ca.ntro.core.graphs.graph_writer;

import ca.ntro.core.graphs.generics.graph.GenericNode;
import ca.ntro.core.reflection.object_graph.ObjectNode;

public class      StructureSpecNtro 
      
       extends    NodeSpecNtro

       implements StructureSpec {

	public StructureSpecNtro(GenericNode<?, ?, ?> node) {
		super(node);
	}

	@Override
	public void addAttribute(String attributeName, String attributeValue) {
		
	}

	@Override
	public String addToList(String indexName, ObjectNode objectNode) {
		return null;
	}

	@Override
	public void addToMap(String keyName, ObjectNode objectNode) {
	}
}

