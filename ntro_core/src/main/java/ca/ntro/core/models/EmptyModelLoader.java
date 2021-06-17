package ca.ntro.core.models;

import source.T;

public class EmptyModelLoader extends ModelLoader {

	public EmptyModelLoader() {
		super(null, null);
	}

	@Override
	protected void runTaskAsync() {
		T.call(this);
		
		// Never load. This task is meant
		// to be replaced by an actual model
		// loader
	}


}
