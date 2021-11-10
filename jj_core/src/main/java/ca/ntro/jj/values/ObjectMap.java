package ca.ntro.jj.values;

import ca.ntro.jj.identifyers.ObjectId;

public interface ObjectMap {

	<O extends Object> O getSingleton(ClassId<O> classId);
	<O extends Object> O getObject(ObjectId<O> objectId);

}
