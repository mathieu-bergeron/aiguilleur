package ca.ntro.jj.tasks.base;

import ca.ntro.jj.common.ExceptionDelayer;
import ca.ntro.jj.tasks.meta.AtomicTaskMeta;
import ca.ntro.jj.tasks.meta.TaskMeta;

public interface Task<TM extends TaskMeta<TM, ATM>, ATM extends AtomicTaskMeta> 
       extends   TaskMeta<TM, ATM>, ExceptionDelayer<TM> {


}
