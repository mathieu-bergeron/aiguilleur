package ca.ntro.jj.tasks.base;


import ca.ntro.jj.common.notifyiers.Notifyier;
import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.tasks.generic.GenericAtomicTask;

public interface AtomicTask extends GenericAtomicTask {

	void execute(ObjectMap previousResults, Notifyier notifyier);
	ObjectMap executeBlocking(ObjectMap previousResults);

}
