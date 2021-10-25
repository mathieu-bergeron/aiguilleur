package ca.ntro.jj.init;

public class InitializationOptionsJj implements InitializationOptions {
	
	private boolean prod = false;

	@Override
	public void setProd(boolean prod) {
		this.prod = prod;
	}

}
