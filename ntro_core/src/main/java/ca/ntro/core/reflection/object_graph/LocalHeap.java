package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.path.Path;

public interface LocalHeap {

	ObjectNode findOrCreateNode(Path attributePath, Object object);


}
