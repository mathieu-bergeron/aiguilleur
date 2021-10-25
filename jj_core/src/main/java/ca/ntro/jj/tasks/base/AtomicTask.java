package ca.ntro.jj.tasks.base;


import ca.ntro.jj.notifyers.Notifyier;
import ca.ntro.jj.tasks.generic.GenericAtomicTask;
import ca.ntro.jj.values.ObjectMap;

public interface AtomicTask extends GenericAtomicTask {

	void execute(ObjectMap previousResults, Notifyier notifyier);
	ObjectMap executeBlocking(ObjectMap previousResults);

}
