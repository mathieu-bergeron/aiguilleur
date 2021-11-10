package ca.ntro.jj.identifyers;

public class ObjectId<O extends Object> extends Id {

	public static final String CATEGORY = "objects";
	
	private Class<O> _class;
	
	protected ObjectId(Class<O> _class) {
		this._class = _class;
	}

	public Class<O> _class(){
		return _class;
	}

	@SuppressWarnings("unchecked")
	public static <O extends Object> ObjectId<O> fromObject(O object, String id){
		ObjectId<O> objectId = new ObjectId<O>((Class<O>) object.getClass());
		
		Path categoryPath = Path.fromRawPath(CATEGORY);
		categoryPath.append(Path.fromClassname(objectId._class().getName()));

		objectId.setCategoryPath(categoryPath);
		objectId.setEntityPath(Path.fromRawPath(id));

		return objectId;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o instanceof ObjectId) {
			ObjectId c = (ObjectId) o;
			
			if(_class != null ? !_class.equals(c._class) : c._class != null) {
				return false;
			}

			return super.equals(c);
		}

		return false;
	}

}
