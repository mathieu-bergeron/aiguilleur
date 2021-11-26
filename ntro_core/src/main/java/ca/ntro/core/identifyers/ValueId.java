package ca.ntro.core.identifyers;

import ca.ntro.core.path.ValuePath;

public class ValueId<V extends Object> {

	private ValuePath path;
	private ObjectId<V> objectId;

	public ValuePath getPath() {
		return path;
	}
	public void setPath(ValuePath path) {
		this.path = path;
	}
	public ObjectId<V> getObjectId() {
		return objectId;
	}
	public void setObjectId(ObjectId<V> objectId) {
		this.objectId = objectId;
	}
	
	

}
