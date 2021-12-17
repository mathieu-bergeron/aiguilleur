package ca.ntro.core.identifyers;

public class IdNtro implements Id  {
	
	private Key key;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	
	
	
	public IdNtro() {
	}

	public IdNtro(Key key) {
		setKey(key);
	}

	public IdNtro(String id) {
		setKey(new Key(id));
	}


	@Override
	public Key toKey() {
		return getKey();
	}

}
