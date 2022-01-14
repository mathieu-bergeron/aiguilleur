package ca.ntro.core.object_diff;

import java.util.ArrayList;
import java.util.List;

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
		
		getTarget().visitNodes().forEach(visitedNode -> {
			
			Walk<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions> walked = visitedNode.walked();
			
			WalkId walk = walked.id();
			
			getSource().walk(walk).forEach(walkInProgress -> {
				
				// We reached the same node in the other object
				if(walkInProgress.walked().size() == walk.size()) {
					
					if(visitedNode.node().isSimpleValue()
							&& !visitedNode.node().asSimpleValue().isNull()
							&& walkInProgress.currentNode().isSimpleValue()
							&& !walkInProgress.currentNode().asSimpleValue().isNull()
							&& !visitedNode.node().asSimpleValue().value().equals(walkInProgress.currentNode().asSimpleValue().value())) {
						
						updates.add(new ModifyNtro(walk.toPath(), visitedNode.node().asSimpleValue().value()));
					}
					
					else if(visitedNode.node().isList()
							&& walkInProgress.currentNode().isList()) {
						
						Path baseValuePath = walk.toPath();
						
						for(Edit edit : EditDistance.editSequence(walkInProgress.currentNode().asList(), visitedNode.node().asList())) {
							
							Path valuePath = Path.fromPath(baseValuePath);
							valuePath.addName(String.valueOf(edit.index()));
							
							if(edit.isModify()) {
								updates.add(new ModifyNtro(valuePath, edit.asModify().value()));
							}

							else if(edit.isInsert()) {
								updates.add(new InsertNtro(valuePath, edit.asInsert().value()));
							}

							else if(edit.isDelete()) {
								updates.add(new DeleteNtro(valuePath));
							}
						}
					}
				}
			});
		});
		
		
		

	}
	
	
	
	
	
	
	

}
