package ca.ntro.core.values;

import ca.ntro.core.identifyers.ClassId;
import ca.ntro.core.identifyers.ObjectId;

public interface ObjectMap {

	<O extends Object> O getSingleton(ClassId<O> classId);
	<O extends Object> O getObject(ObjectId<O> objectId);

}
