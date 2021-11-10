package ca.ntro.jj.identifyers;

import ca.ntro.jj.values.ClassId;

public class ObjectIdJj {
	
	private ClassId classId;

	public ObjectIdJj(ClassId classId, String id) {
		this.classId = classId;
	}
	
	public ObjectIdJj(Class<?> _class, String id) {
		//this.classId = new ClassIdJj(_class);
	}

	public Class<?> _class() {
		return null;
	}

	public ClassId classId() {
		return classId;
	}
}
