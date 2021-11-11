package ca.ntro.core.tasks.base;


import ca.ntro.core.notifyers.Notifyier;
import ca.ntro.core.tasks.generic.GenericAtomicTask;
import ca.ntro.core.values.ObjectMap;

public interface AtomicTask extends GenericAtomicTask {

	void execute(ObjectMap previousResults, Notifyier notifyier);
	ObjectMap executeBlocking(ObjectMap previousResults);

}
