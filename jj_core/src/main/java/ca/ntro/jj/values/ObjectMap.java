package ca.ntro.jj.values;

import ca.ntro.jj.identifyers.ObjectIdJj;

public interface ObjectMap {

	<O extends Object> O getSingleton(ClassId<O> classId);
	<O extends Object> O getObject(ObjectIdJj objectId);

}
