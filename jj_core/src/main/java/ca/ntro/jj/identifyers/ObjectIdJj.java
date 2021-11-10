package ca.ntro.jj.identifyers;

import ca.ntro.jj.values.ClassId;

public class ObjectIdJj extends SimpleIdJj implements ObjectId {
	
	private ClassId classId;

	public ObjectIdJj(ClassId classId, String id) {
		super(id);
		this.classId = classId;
	}
	
	public ObjectIdJj(Class<?> _class, String id) {
		super(id);
		this.classId = new ClassIdJj(_class);
	}

	@Override
	public Class<?> _class() {
		return classId._class();
	}

	@Override
	public ClassId classId() {
		return classId;
	}
}
