package ca.ntro.jj.identifyers;

import ca.ntro.jj.values.ClassId;

public class ClassIdJj<O extends Object> implements ClassId<O> {
	
	private Class<O> _class;
	
	public ClassIdJj(Class<O> _class) {
		this._class = _class;
	}

	@Override
	public Class<O> _class() {
		return _class;
	}


}
