package ca.ntro.jj.identifyers;

public class ClassIdJj implements ClassId {
	
	private Class<?> _class;
	
	public ClassIdJj(Class<?> _class) {
		this._class = _class;
	}

	@Override
	public Class<?> _class() {
		return _class;
	}


}
