package ca.ntro.jj.tasks.base;

import ca.ntro.jj.common.exceptions.ExceptionCatcher;
import ca.ntro.jj.common.values.ObjectMap;
import ca.ntro.jj.tasks.meta.TaskMeta;
import ca.ntro.jj.wrappers.future.Future;

public interface Task<T extends Task<?,?>, AT extends AtomicTask>
       extends   TaskMeta<T, AT>, ExceptionCatcher<T> {

	Future<ObjectMap> execute();
	ObjectMap executeBlocking();

}
