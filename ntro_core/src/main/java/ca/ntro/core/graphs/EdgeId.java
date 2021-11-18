package ca.ntro.core.graphs;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.path.Filepath;
import ca.ntro.core.path.Path;
import ca.ntro.core.path.PathName;

public class EdgeId implements Id {
	
	private NodeId fromId = null;
	private PathName edgeName = null;
	private NodeId toId = null;

	protected NodeId getFromId() {
		return fromId;
	}

	protected void setFromId(NodeId fromId) {
		this.fromId = fromId;
	}

	protected PathName getEdgeName() {
		return edgeName;
	}

	protected void setEdgeName(PathName edgeName) {
		this.edgeName = edgeName;
	}

	protected NodeId getToId() {
		return toId;
	}

	protected void setToId(NodeId toId) {
		this.toId = toId;
	}
	
	public EdgeId(NodeId fromId, PathName edgeName) {
		setFromId(fromId);
		setEdgeName(edgeName);
	}

	public EdgeId(NodeId fromId, PathName edgeName, NodeId toId) {
		setFromId(fromId);
		setEdgeName(edgeName);
		setToId(toId);
	}
	

	public NodeId fromId() {
		return getFromId();
	}

	public NodeId toId() {
		return getToId();
	}
	
	public boolean isPrefisOfEdgeWalk(EdgeWalk edgeWalk) {
		return edgeWalk.startsWith(edgeName.name());
	}

	
	@Override
	public String toKey() {
		return toFilepath().toKey();
	}

	@Override
	public String toHtmlId() {
		return toFilepath().toHtmlId();
	}

	@Override
	public Filepath toFilepath() {

		Filepath path = Filepath.emptyPath();
		path.append(Filepath.fromPath(getFromId().toFilepath()));
		path.append(Filepath.fromSingleName(edgeName.toKey()));
		
		if(getToId() != null) {
			path.append(Filepath.fromPath(getToId().toFilepath()));
		}
		
		return path;
	}

	public PathName edgeName() {
		return edgeName;
	}
}
