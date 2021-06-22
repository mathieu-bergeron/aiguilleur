package ca.ntro.jj.tasks.base;


import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.tasks.meta.AtomicTaskMeta;

public interface AtomicTask extends AtomicTaskMeta {

	void execute(ObjectMap previousResults, Notifyier notifyier);
	ObjectMap executeBlocking(ObjectMap previousResults);

}
