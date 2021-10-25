package ca.ntro.jj.notifyers;

import ca.ntro.jj.values.ObjectMap;

public interface Notifyier {

	void onDone();
	void onDone(ObjectMap results);
	void onException(Throwable t);

}
