package ca.ntro.core.identifyers;

import ca.ntro.core.path.Path;

public class ClassId<O extends Object> extends IdPathNtro {
	
	private Class<O> _class;
	
	protected ClassId(Class<O> _class) {
		super();

		this._class = _class;
	}

	public Class<O> _class(){
		return _class;
	}

	public static <O extends Object> ClassId<O> fromClass(Class<O> _class){
		ClassId<O> classId = new ClassId<O>(_class);
		
		classId.setEntityPath(Path.fromClassname(_class.getName()));

		return classId;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof ClassId) {
			ClassId<?> c = (ClassId<?>) o;
			
			if(_class != null ? !_class.equals(c._class) : c._class != null) {
				return false;
			}

			return super.equals(c);
		}

		return false;
	}
}
