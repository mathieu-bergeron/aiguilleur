package ca.ntro.core.graphs.common;

import ca.ntro.core.identifyers.Key;

public class NodeIdNtro implements NodeId {
	
	private Key key;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	public NodeIdNtro(Key key) {
		setKey(key);
	}

	public NodeIdNtro(String key) {
		setKey(new Key(key));
	}

	@Override
	public Key toKey() {
		return getKey();
	}

	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof NodeId) {
			NodeId n = (NodeId) o;
			
			if(n.toKey() == null && key != null) {
				return false;
			}

			if(n.toKey() != null && !n.toKey().equals(key)) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
}
