package ca.ntro.jj.common.notifyiers;

import ca.ntro.jj.common.values.ObjectMap;

public interface Notifyier {

	void onDone();
	void onDone(ObjectMap results);
	void onException(Throwable t);

}
