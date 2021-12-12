package ca.ntro.core.graphs.graph_writer;

import ca.ntro.core.reflection.object_graph.ObjectNode;

public interface StructureSpec extends NodeSpec {

	void addAttribute(String attributeName, String attributeValue);

	String addToList(String indexName, ObjectNode objectNode);

	void addToMap(String keyName, ObjectNode objectNode);

}
