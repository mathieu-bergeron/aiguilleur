package ca.ntro.core.notifyers;

import ca.ntro.core.values.ObjectMap;

public interface Notifyier {

	void onDone();
	void onDone(ObjectMap results);
	void onException(Throwable t);

}
