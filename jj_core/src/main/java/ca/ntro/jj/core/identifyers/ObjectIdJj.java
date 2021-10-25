package ca.ntro.jj.core.identifyers;


public class ObjectIdJj<O extends Object> extends SimpleIdJj implements ObjectId<O> {
	
	private ClassId<O> classId;

	public ObjectIdJj(ClassId<O> classId, String id) {
		super(id);
		this.classId = classId;
	}
	
	public ObjectIdJj(Class<O> _class, String id) {
		super(id);
		this.classId = new ClassIdJj<O>(_class);
	}

	@Override
	public Class<O> _class() {
		return classId._class();
	}

	@Override
	public ClassId<O> classId() {
		return classId;
	}
}
