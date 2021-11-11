package ca.ntro.core.tasks.base;

import ca.ntro.core.exceptions.ExceptionCatcher;
import ca.ntro.core.tasks.generic.GenericTask;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;

public interface Task<T extends Task<?,?>, AT extends AtomicTask>
       extends   GenericTask<T, AT>, ExceptionCatcher<T> {

	Future<ObjectMap> execute();
	ObjectMap executeBlocking();

}
