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
}
