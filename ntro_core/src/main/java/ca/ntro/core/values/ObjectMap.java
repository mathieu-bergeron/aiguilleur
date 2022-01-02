package ca.ntro.core.values;


import ca.ntro.core.identifyers.Id;
import ca.ntro.core.stream.Stream;

public interface ObjectMap {

	boolean contains(Id id);
	boolean contains(String id);

	<O extends Object> O get(Class<O> _class, Id id);
	<O extends Object> O get(Class<O> _class, String id);

	Object get(Id id);
    Object get(String id);

	Stream<String> ids();
	Stream<Object> objects();

	/*
	<O extends Object> O getSingleton(ClassId<O> classId);
	<O extends Object> O getObject(ObjectId<O> objectId);
	*/

}
