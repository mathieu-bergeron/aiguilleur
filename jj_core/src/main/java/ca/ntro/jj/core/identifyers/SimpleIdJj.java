package ca.ntro.jj.core.identifyers;

public class SimpleIdJj implements SimpleId {
	
	private String id;
	
	public SimpleIdJj(String id) {
		this.id = id;
	}

	@Override
	public String id() {
		return id;
	}

}
