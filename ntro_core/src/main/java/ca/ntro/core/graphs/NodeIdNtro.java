package ca.ntro.core.graphs;

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
	public String label() {
		return toKey().toString();
	}

	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof NodeIdNtro) {
			NodeIdNtro n = (NodeIdNtro) o;
			
			if(n.key == null && key != null) {
				return false;
			}

			if(n.key != null && !n.key.equals(key)) {
				return false;
			}
			
			return true;
		}
		
		return false;
	}
}
