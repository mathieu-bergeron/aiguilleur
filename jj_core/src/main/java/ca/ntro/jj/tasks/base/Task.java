package ca.ntro.jj.tasks.base;

import ca.ntro.jj.common.ExceptionDelayer;
import ca.ntro.jj.tasks.meta.TaskMeta;

public interface Task extends TaskMeta<Task, AtomicTask>, ExceptionDelayer {


}
