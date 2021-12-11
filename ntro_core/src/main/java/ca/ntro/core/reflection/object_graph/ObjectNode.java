package ca.ntro.core.reflection.object_graph;

import java.util.List;
import java.util.Map;

import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedNode;

public interface ObjectNode 

       extends   GenericDirectedNode<ObjectNode, 
                                     ReferenceEdge,
                                     ObjectGraphSearchOptions> ,

                 GenericObjectNode {

	boolean isList();
	boolean isMap();
	boolean isUserDefinedObject();
	boolean isSimpleValue();
	
	ObjectNodeSimpleValue asSimpleValue();

	List<?>           asList();
	<I> List<I>       asList(Class<I> itemClass);

	Map<String,?>     asMap();
	<V> Map<String,V> asMap(Class<V> valueClass);

	Object            asUserDefinedObject();
	<V> V             asUserDefinedObject(Class<V> _class);

}

