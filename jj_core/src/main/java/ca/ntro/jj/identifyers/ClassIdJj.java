package ca.ntro.jj.identifyers;


public class ClassIdJj<O extends Object> {
	
	private Class<O> _class;
	
	public ClassIdJj(Class<O> _class) {
		this._class = _class;
	}

	public Class<O> _class() {
		return _class;
	}


}
