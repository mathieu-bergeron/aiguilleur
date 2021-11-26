package ca.ntro.core.task_graphs.executable_task_graph;

import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;

public interface  ExecutableTaskGraph<ET extends ExecutableTask<EAT>, EAT extends ExecutableAtomicTask>
       extends    TaskGraph<ET,EAT> {


	Future<ObjectMap> execute();

	ObjectMap executeBlocking() throws Throwable;

}
