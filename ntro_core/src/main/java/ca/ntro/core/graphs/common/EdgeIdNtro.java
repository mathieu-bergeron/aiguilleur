package ca.ntro.core.graphs.common;

import ca.ntro.core.identifyers.Key;

public class EdgeIdNtro implements EdgeId {
	
	private Key key;
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public EdgeIdNtro(Key key) {
		setKey(key);
	}

	public EdgeIdNtro(String key) {
		setKey(new Key(key));
	}

	@Override
	public Key toKey() {
		return getKey();
	}

}
