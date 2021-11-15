package ca.ntro.core.graphs.hierarchical_dag;

import ca.ntro.core.graphs.generic_graph.NodeId;
import ca.ntro.core.path.Path;

public class HierarchicalNodeId extends NodeId {

	public HierarchicalNodeId(String id) {
		super(id);
	}
	
	public void updateParentId(HierarchicalNodeId parentId) {
		
		Path updatedPath = parentId.parentPath();
		if(updatedPath == null) {
			updatedPath = Path.emptyPath();
		}

		updatedPath.append(leafId());
		
		setEntityPath(updatedPath);
	}

	public Path parentPath() {
		return getEntityPath().parent();
	}

	public Path leafId() {
		return Path.fromSingleName(getEntityPath().lastName());
	}
}
