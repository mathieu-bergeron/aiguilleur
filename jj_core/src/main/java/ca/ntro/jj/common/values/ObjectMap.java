package ca.ntro.jj.common.values;

import ca.ntro.jj.common.identifyiers.ClassId;
import ca.ntro.jj.common.identifyiers.ObjectId;

public interface ObjectMap {

	<O extends Object> O getSingleton(Class<O> _class);
	<O extends Object> O getSingleton(ClassId<O> classId);

	<O extends Object> O getObject(Class<O> _class, String id);
	<O extends Object> O getObject(ObjectId<O> objectId);

}
