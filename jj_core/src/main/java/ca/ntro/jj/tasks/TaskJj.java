package ca.ntro.jj.tasks;

import ca.ntro.jj.common.ExceptionDelayerJj;
import ca.ntro.jj.tasks.base.AtomicTask;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.tasks.meta.TaskMeta;
import ca.ntro.jj.tasks.results.NamedResults;

public class TaskJj<TM extends TaskMeta<TM, AtomicTask<?>>> extends ExceptionDelayerJj<TM> implements Task<TM, AtomicTask<?>>, NamedResults {

	@Override
	public TM addPreviousTask(TM previousTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TM addEntryTask(AtomicTask<?> entryTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TM addSubTask(TM subTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TM addExitTask(AtomicTask<?> exitTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TM addNextTask(TM nextTask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <R> R getResult(Class<R> resultClass, String resultName) {
		
		/*
		 * TODO: search the graph for completed tasks that have 
		 *       yielded the results
		 */

		return null;
	}

}
