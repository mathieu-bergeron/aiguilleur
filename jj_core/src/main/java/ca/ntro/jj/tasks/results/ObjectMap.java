package ca.ntro.jj.tasks.results;

import ca.ntro.jj.managed_objects.ManagedObject;
import ca.ntro.jj.managed_objects.ObjectDescriptor;

public interface ObjectMap {

	<MO extends ManagedObject> MO get(Class<MO> _class, String id);
	<MO extends ManagedObject> MO get(ObjectDescriptor<MO> objectDescriptor);

}
