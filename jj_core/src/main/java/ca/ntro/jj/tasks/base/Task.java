package ca.ntro.jj.tasks.base;

import ca.ntro.jj.core.exceptions.ExceptionCatcher;
import ca.ntro.jj.core.values.ObjectMap;
import ca.ntro.jj.tasks.generic.GenericTask;
import ca.ntro.jj.wrappers.future.Future;

public interface Task<T extends Task<?,?>, AT extends AtomicTask>
       extends   GenericTask<T, AT>, ExceptionCatcher<T> {

	Future<ObjectMap> execute();
	ObjectMap executeBlocking();

}
