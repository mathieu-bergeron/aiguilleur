package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.path.Path;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class ObjectGraphStructureNtro implements ObjectGraphStructure {

	private Object[] startObjects;
	private LocalHeap localHeap = createLocalHeap();

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

	protected abstract LocalHeap createLocalHeap();

	@Override
	public <R> void reduceStartNodes(ResultNtro<R> result, 
			                         NodeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder, R> reducer) {

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

	private <R> void _reduceStartNode(ResultNtro<R> result, Path objectPath, Object object, NodeReducer<ObjectNode, ReferenceEdge, ObjectGraphSearchOptionsBuilder, R> reducer) {
		if(result.hasException()) {
			return;
		}

		try {

			result.registerValue(reducer.reduceNode(result.value(), getLocalHeap().findOrCreateNode(this, objectPath, object)));

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

}
