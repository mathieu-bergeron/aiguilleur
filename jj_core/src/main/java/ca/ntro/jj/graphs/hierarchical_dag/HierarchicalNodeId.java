package ca.ntro.jj.graphs.hierarchical_dag;

import ca.ntro.jj.graphs.dag.NodeId;
import ca.ntro.jj.identifyers.Path;

public class HierarchicalNodeId extends NodeId {

	public HierarchicalNodeId(String id) {
		super(id);
	}
	
	public void updateParentId(HierarchicalNodeId parentId) {
		
		Path updatedPath = parentId.parentPath();
		if(updatedPath == null) {
			updatedPath = new Path();
		}

		updatedPath.append(leafId());
		
		setEntityPath(updatedPath);
	}

	public Path parentPath() {
		return getEntityPath().subPath(0, getEntityPath().nameCount() - 1);
	}

	public Path leafId() {
		return Path.fromSingleName(getEntityPath().lastName());
	}
}
