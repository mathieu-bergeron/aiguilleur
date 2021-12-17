package ca.ntro.core.values;

import ca.ntro.core.identifyers.Id;

public interface MutableObjectMap extends ObjectMap {

	void registerObject(String id, Object object);
	void registerObject(Id id, Object object);

}
