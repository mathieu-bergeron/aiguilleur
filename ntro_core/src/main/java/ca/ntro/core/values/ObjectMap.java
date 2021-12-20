package ca.ntro.core.values;

import ca.ntro.core.identifyers.Id;

public interface ObjectMap {

	boolean containsObject(Id id);
	boolean containsObject(String id);

	<O extends Object> O getObject(Class<O> _class, Id id);
	<O extends Object> O getObject(Class<O> _class, String id);

	void merge(ObjectMap other);
	

	/*
	<O extends Object> O getSingleton(ClassId<O> classId);
	<O extends Object> O getObject(ObjectId<O> objectId);
	*/

}
