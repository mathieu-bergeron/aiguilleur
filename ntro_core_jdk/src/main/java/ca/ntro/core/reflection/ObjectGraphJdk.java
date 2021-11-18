package ca.ntro.core.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ntro.core.exceptions.Break;
import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.EdgeReducer;
import ca.ntro.core.graphs.EdgeVisitor;
import ca.ntro.core.graphs.EdgeWalkReducer;
import ca.ntro.core.graphs.EdgeWalkVisitor;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeMatcher;
import ca.ntro.core.graphs.NodeReducer;
import ca.ntro.core.graphs.NodeVisitor;
import ca.ntro.core.graphs.ReachableEdgeReducer;
import ca.ntro.core.graphs.ReachableEdgeVisitor;
import ca.ntro.core.graphs.ReachableNodeReducer;
import ca.ntro.core.graphs.ReachableNodeVisitor;
import ca.ntro.core.graphs.directed_graph.DirectedGraphSearchOptions;
import ca.ntro.core.graphs.generic_graph.NodeNtro;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.path.Path;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ObjectGraphJdk implements ObjectGraph {
	
	private Object object;
	
	public ObjectGraphJdk(Object object) {
		this.object = object;
	}
	

	@Override
	public GraphId id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<ObjectValue> findNode(NodeId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<ObjectValue> findNode(NodeMatcher<ObjectValue> matcher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<ObjectValue> findNode(ObjectValue nodeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<ObjectValue> findNode(String rawNodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachNode(NodeVisitor<ObjectValue> visitor) {
		
		Node<ObjectValue> rootNode = createNode(Path.emptyPath(), object);
		
		try {

			visitor.visitNode(rootNode);

			forEachReachableNode(rootNode, (edgeWalked, n) -> {
				visitor.visitNode(n);
			});

		} catch (Break e) {}
	}

	@Override
	public void forEachEdge(EdgeVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceNodes(R initialValue, NodeReducer<ObjectValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> recudeEdges(R initialValue, EdgeReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableNode(Node<ObjectValue> from, ReachableNodeVisitor<ObjectValue, ReferenceValue> visitor) {
		
		reduceReachableNodes(from, null, (accumulator, distance, n) -> {
			
			visitor.visitReachableNode(distance, n);

			return accumulator;
		});
	}

	@Override
	public void forEachReachableNode(Node<ObjectValue> from, DirectedGraphSearchOptions options,
			ReachableNodeVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<ObjectValue> fromNode, 
			                                  R initialValue, 
			                                  ReachableNodeReducer<ObjectValue, ReferenceValue, R> reducer) {
		
		ResultNtro<R> result = new ResultNtro<R>(initialValue);

		Object rootObject = fromNode.value().object();
		
		List<Object> visitedObject = new ArrayList<>();
		visitedObject.add(rootObject);
		
		reduceReachableNodes(visitedObject, rootObject, result, Path.emptyPath(), reducer);

		return result;
	}
	
	private NodeNtro<ObjectValue> createNode(Path attributePath, Object attributeValue){

		ObjectValue objectValue = new ObjectValue(attributeValue);
		NodeId nodeId = new NodeId(attributePath);
		NodeNtro<ObjectValue> node = new NodeNtro<ObjectValue>(nodeId, objectValue);
		
		return node;
	}
	
	protected boolean isObjectAlreadyVisited(List<Object> visitedObjects, Object target) {
		boolean visited = false;
		
		for(Object candidate : visitedObjects) {
			if(candidate == target) {
				visited = true;
				break;
			}
		}
		
		return visited;
	}

	public <R> void reduceReachableNodes(List<Object> visitedObjects,
										 Object currentObject,
			                             ResultNtro<R> result, 
			                             Path previousAttributePath,
			                             ReachableNodeReducer<ObjectValue, ReferenceValue, R> reducer) {
		
		if(result.hasException()) {
			return;
		}
		
		for(Method method: currentObject.getClass().getMethods()) {
			
			if(method.getName().startsWith("get")
					&& method.getName().length() > 3
					&& !method.getName().equals("getClass")) {
				
				String rawAttributeName = method.getName().substring(3);
				String attributeName = rawAttributeName.substring(0,1).toLowerCase();
				attributeName += rawAttributeName.substring(1);
				
				try {

					Object attributeValue = method.invoke(currentObject);
					
					Path attributePath = Path.fromPath(previousAttributePath);
					attributePath.addName(attributeName);
					
					NodeNtro<ObjectValue> node = createNode(attributePath, attributeValue);
					
					if(isObjectAlreadyVisited(visitedObjects, attributeValue)) {

						result.registerValue(reducer.reduceReachableNode(result.value(), attributePath.nameCount(), node));
						visitedObjects.add(attributeValue);
						
						reduceReachableNodes(visitedObjects, attributeValue, result, attributePath, reducer);
					}

				} catch (Break e) {

					break;

				} catch (Throwable t) {
					
					result.registerException(t);
					break;
				}
				
				
			}
		}
		
		

	}

	@Override
	public <R> Result<R> reduceReachableNodes(Node<ObjectValue> from, DirectedGraphSearchOptions options,
			R initialValue, ReachableNodeReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void forEachReachableEdge(Node<ObjectValue> from,
			ReachableEdgeVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forEachReachableEdge(Node<ObjectValue> from, DirectedGraphSearchOptions options,
			ReachableEdgeVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<ObjectValue> from, R initialValue,
			ReachableEdgeReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceReachableEdges(Node<ObjectValue> from, 
			                                  DirectedGraphSearchOptions options, 
			                                  R initialValue, 
			                                  ReachableEdgeReducer<ObjectValue, 
			                                  ReferenceValue, R> reducer) {

		return null;
	}

	@Override
	public void visitEdgeWalk(Node<ObjectValue> from, EdgeWalk edgeWalk,
			EdgeWalkVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitEdgeWalk(Node<ObjectValue> from, Direction direction, EdgeWalk edgeWalk,
			EdgeWalkVisitor<ObjectValue, ReferenceValue> visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<ObjectValue> from, EdgeWalk edgeWalk, R initialValue,
			EdgeWalkReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> Result<R> reduceEdgeWalk(Node<ObjectValue> from, Direction direction, EdgeWalk edgeWalk, R initialValue,
			EdgeWalkReducer<ObjectValue, ReferenceValue, R> reducer) {
		// TODO Auto-generated method stub
		return null;
	}

}
