package ca.ntro.jj.core.notifyers;

import ca.ntro.jj.core.values.ObjectMap;

public interface Notifyier {

	void onDone();
	void onDone(ObjectMap results);
	void onException(Throwable t);

}
