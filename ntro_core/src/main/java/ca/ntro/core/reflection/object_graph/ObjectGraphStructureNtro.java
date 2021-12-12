package ca.ntro.core.reflection.object_graph;


import ca.ntro.core.path.Path;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.stream.StreamNtro;
import ca.ntro.core.stream.Visitor;

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
