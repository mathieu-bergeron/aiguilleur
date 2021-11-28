package ca.ntro.core.graphs;

import ca.ntro.core.identifyers.Id;
import ca.ntro.core.identifyers.Key;
import ca.ntro.core.path.EdgeWalk;
import ca.ntro.core.path.Filepath;
import ca.ntro.core.path.Path;

public class EdgeId implements Id {
	
	private NodeId fromId = null;
	private Key edgeName = null;
	private NodeId toId = null;

	protected NodeId getFromId() {
		return fromId;
	}

	protected void setFromId(NodeId fromId) {
		this.fromId = fromId;
	}

	protected Key getEdgeName() {
		return edgeName;
	}

	protected void setEdgeName(Key edgeName) {
		this.edgeName = edgeName;
	}

	protected NodeId getToId() {
		return toId;
	}

	protected void setToId(NodeId toId) {
		this.toId = toId;
	}
	
	public EdgeId(NodeId fromId, Key edgeName) {
		setFromId(fromId);
		setEdgeName(edgeName);
	}

	public EdgeId(NodeId fromId, Key edgeName, NodeId toId) {
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
		return edgeWalk.startsWith(edgeName.toString());
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
		path.append(Filepath.fromSingleName(edgeName.toString()));
		
		if(getToId() != null) {
			path.append(Filepath.fromPath(getToId().toFilepath()));
		}
		
		return path;
	}

	public Key edgeName() {
		return edgeName;
	}
}
