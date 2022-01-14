package ca.ntro.core.object_diff;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ntro.core.edit_distance.EditDistance;
import ca.ntro.core.edit_distance.edits.Edit;
import ca.ntro.core.graphs.generics.graph.Walk;
import ca.ntro.core.graphs.generics.graph.WalkId;
import ca.ntro.core.object_diff.updates.ObjectUpdate;
import ca.ntro.core.path.Path;
import ca.ntro.core.object_diff.updates.DeleteNtro;
import ca.ntro.core.object_diff.updates.InsertNtro;
import ca.ntro.core.object_diff.updates.ModifyNtro;
import ca.ntro.core.reflection.object_graph.ObjectGraph;
import ca.ntro.core.reflection.object_graph.ObjectGraphSearchOptions;
import ca.ntro.core.reflection.object_graph.ObjectNode;
import ca.ntro.core.reflection.object_graph.ReferenceEdge;

public class ObjectDiffNtro implements ObjectDiff {
	

	private ObjectGraph source;
	private ObjectGraph target;
	
	private List<ObjectUpdate> updates;
	

	public ObjectGraph getSource() {
		return source;
	}

	public void setSource(ObjectGraph source) {
		this.source = source;
	}

	public ObjectGraph getTarget() {
		return target;
	}

	public void setTarget(ObjectGraph target) {
		this.target = target;
	}






	public ObjectDiffNtro(ObjectGraph source, ObjectGraph target) {
		setSource(source);
		setTarget(target);
	}
	
	
	
	

	@Override
	public List<ObjectUpdate> updates() {
		if(updates == null) {
			computeUpdates();
		}
		
		return updates;
	}
	
	
	
	

	private void computeUpdates() {
		updates = new ArrayList<>();
		
		// FIXME: must maintain a localHeap to detect when multiple attributes point to the same object
		//        must not give update twice for the values of that object
		
		getTarget().visitNodes().forEach(visitedNode -> {
			
			Walk<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions> walked = visitedNode.walked();
			
			WalkId walk = walked.id();
			
			getSource().walk(walk).forEach(walkInProgress -> {
				
				if(walkInProgress.remainingWalk().isEmpty()) {
					
					addUpdatesForCorrespondingNodes(walk.toPath(), walkInProgress.currentNode(), visitedNode.node());
					
				}else if(!walkInProgress.hasCurrentNode()){
					// The target value does not exists in the source graph
					// We need to insert it
					updates.add(new InsertNtro(walked.id().toPath(), visitedNode.node().object()));
				}

			});
		});
	}

	private void addUpdatesForCorrespondingNodes(Path valuePath, ObjectNode source, ObjectNode target) {

		if(shouldModifySimpleValue(source, target)) {

			updates.add(new ModifyNtro(valuePath, target.asSimpleValue().value()));
		}

		else if(source.isList()
				&& target.isList()) {
			
			addUpdatesForCorrespondingLists(valuePath, source.asList(), target.asList());
		}

		else if(source.isMap()
				&& target.isMap()) {
			
			addUpdatesForCorrespondingMaps(valuePath, source.asMap(), target.asMap());
		}
	}

	private void addUpdatesForCorrespondingLists(Path valuePath, List<?> source, List<?> target) {

		for(Edit edit : EditDistance.editSequence(source, target)) {
									
			Path itemPath = Path.fromPath(valuePath);
			itemPath.addName(String.valueOf(edit.index()));
			
			if(edit.isModify()) {
				updates.add(new ModifyNtro(itemPath, edit.asModify().value()));
			}

			else if(edit.isInsert()) {
				updates.add(new InsertNtro(itemPath, edit.asInsert().value()));
			}

			else if(edit.isDelete()) {
				updates.add(new DeleteNtro(itemPath));
			}
		}
	}

	private void addUpdatesForCorrespondingMaps(Path valuePath, Map<String, ?> source, Map<String, ?> target) {
		
		throw new RuntimeException("Maps covered by 'normal' ObjectGraph visit. Only Lists are special cases.");

	}


	private boolean shouldModifySimpleValue(ObjectNode source, ObjectNode target) {
		return source.isSimpleValue() 
				&& !source.asSimpleValue().isNull() 
				&& target.isSimpleValue() 
				&& !target.asSimpleValue().isNull() 
				&& !source.asSimpleValue().value().equals(target.asSimpleValue().value());
	}

	
	
	
	
	
	
	

}
