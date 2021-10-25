package ca.ntro.jj.initialization;

public class InitializerOptionsJj implements InitializerOptions {
	
	private boolean prod = false;

	@Override
	public void setProd(boolean prod) {
		this.prod = prod;
	}

}
