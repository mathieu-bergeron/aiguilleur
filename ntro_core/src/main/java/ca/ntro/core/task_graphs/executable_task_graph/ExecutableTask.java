package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.values.ObjectMapNtro;
import ca.ntro.core.wrappers.result.ResultNtro;

public interface ExecutableTask extends Task<ExecutableTaskNtro, ExecutableAtomicTaskNtro> {
	
	boolean execute(ResultNtro<ObjectMapNtro> result);
	boolean suspend(ResultNtro<ObjectMapNtro> result);
	boolean stop(ResultNtro<ObjectMapNtro> result);

}
