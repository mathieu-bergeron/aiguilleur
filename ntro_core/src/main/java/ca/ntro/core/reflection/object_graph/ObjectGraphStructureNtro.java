package ca.ntro.core.reflection.object_graph;


import ca.ntro.core.graphs.generics.graph.NodeReducer;
import ca.ntro.core.path.Path;
import ca.ntro.core.stream.Reducer;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class ObjectGraphStructureNtro implements ObjectGraphStructure {

	private ObjectGraph graph;
	private Object[] startObjects;
	private LocalHeap localHeap = newLocalHeapInstance();

	public ObjectGraphStructureNtro(Object o, ObjectGraph graph) {
		setGraph(graph);
		setStartObjects(new Object[] {o});
	}

	public ObjectGraphStructureNtro() {
	}

	public Object[] getStartObjects() {
		return startObjects;
	}

	public void setStartObjects(Object[] startObjects) {
		this.startObjects = startObjects;
	}

	public LocalHeap getLocalHeap() {
		return localHeap;
	}

	public void setLocalHeap(LocalHeap localHeap) {
		this.localHeap = localHeap;
	}

	public ObjectGraph getGraph() {
		return graph;
	}

	public void setGraph(ObjectGraph graph) {
		this.graph = graph;
	}

	protected abstract LocalHeap newLocalHeapInstance();

	@Override
	public <R> void reduceStartNodes(ResultNtro<R> result, 
			                         NodeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer) {

		if(result.hasException()) {
			return;
		}

		if(startObjects.length == 1) {

			_reduceStartNode(result, Path.emptyPath(), startObjects[0], reducer);

		}else {

			for(int i = 0; i < startObjects.length; i++) {

				if(result.hasException()) {
					return;
				}
				
				Object object = startObjects[i];

				Path objectPath = Path.fromSingleName(String.valueOf(i));
				objectPath.addName(object.getClass().getSimpleName());
				
				_reduceStartNode(result, objectPath, object, reducer);
			}
		}
	}

	@Override
	public Stream<ObjectNode> startNodes() {
		return new StreamNtro<ObjectNode>() {

			@Override
			public void _forEach(Visitor<ObjectNode> visitor) throws Throwable {
				if(startObjects.length == 1) {

					visitStartNode(Path.emptyPath(), startObjects[0], visitor);

				}else {

					for(int i = 0; i < startObjects.length; i++) {
						
						Object object = startObjects[i];

						Path objectPath = Path.fromSingleName(String.valueOf(i));
						objectPath.addName(object.getClass().getSimpleName());
						
						visitStartNode(objectPath, object, visitor);
					}
				}
			}
		};
	}

	private void visitStartNode(Path objectPath, Object object, Visitor<ObjectNode> visitor) throws Throwable {
		visitor.visit(getLocalHeap().findOrCreateNode(getGraph(), objectPath, object, true));
	}

	private <R> void _reduceStartNode(ResultNtro<R> result, Path objectPath, Object object, NodeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, R> reducer) {
		if(result.hasException()) {
			return;
		}

		try {
			
			result.registerValue(reducer.reduceNode(result.value(), getLocalHeap().findOrCreateNode(getGraph(), objectPath, object, true)));

		} catch (Throwable t) {

			result.registerException(t);
		}
	}

	@Override
	public String label() {
		Path labelPath = Path.emptyPath();

		for(Object rootObject : startObjects) {
			labelPath.addName(rootObject.getClass().getSimpleName());
		}

		return labelPath.toHtmlId();
	}

	boolean isStartNode(ObjectNode objectNode) {
		boolean isStartNode = false;
		
		for(Object startObjet : startObjects) {
			if(objectNode.object() == startObjet) {
				isStartNode = true;
				break;
			}
		}
		
		return isStartNode;
	}

	@Override
	public <R> void _reduceStartNodes(ResultNtro<R> result, Reducer<ObjectNode, R> reducer) {
		throw new RuntimeException("TODO");
	}

}
