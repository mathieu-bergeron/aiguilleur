package ca.ntro.core.values;

import ca.ntro.core.identifyers.Id;

public interface ObjectMap {

	boolean contains(Id id);
	boolean contains(String id);

	<O extends Object> O get(Class<O> _class, Id id);
	<O extends Object> O get(Class<O> _class, String id);

	void merge(ObjectMap other);
	

	/*
	<O extends Object> O getSingleton(ClassId<O> classId);
	<O extends Object> O getObject(ObjectId<O> objectId);
	*/

}
