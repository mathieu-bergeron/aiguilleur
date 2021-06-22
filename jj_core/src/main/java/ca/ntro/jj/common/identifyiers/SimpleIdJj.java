package ca.ntro.jj.common.identifyiers;

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
