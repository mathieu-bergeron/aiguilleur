package ca.ntro.jj.core.values;

import ca.ntro.jj.core.identifyers.ClassId;
import ca.ntro.jj.core.identifyers.ObjectId;

public interface ObjectMap {

	<O extends Object> O getSingleton(ClassId<O> classId);
	<O extends Object> O getObject(ObjectId<O> objectId);

}
