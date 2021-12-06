package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.path.Path;

public interface LocalHeap {

	ObjectNode findOrCreateNode(ObjectGraph graph, Path attributePath, Object object);

}
