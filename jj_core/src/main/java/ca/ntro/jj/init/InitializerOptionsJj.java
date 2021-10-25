package ca.ntro.jj.init;

public class InitializerOptionsJj implements InitializerOptions {
	
	private boolean prod = false;

	@Override
	public void setProd(boolean prod) {
		this.prod = prod;
	}

}
