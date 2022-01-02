package ca.ntro.core.task_graphs.task_graph_writer;

import ca.ntro.core.graph_writer.NodeSpecNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptions;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphNode;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTraceNtro;

public class InternalTaskGraphTraceWriterNtro<T  extends GenericTask<T,AT>, 
                                              AT extends GenericAtomicTask<T,AT>>

       extends InternalTaskGraphWriterNtro<T,AT>

       implements InternalTaskGraphTraceWriter<T,AT> {

	private TaskGraphTraceNtro trace;

	public InternalTaskGraphTraceWriterNtro(TaskGraphTraceNtro trace) {
		this.trace = trace;
	}

	@Override
	protected void adjustNodeSpecAttributes(TaskGraphNode<T,AT> node,
			                                HierarchicalDagWriterOptions options,
			                                NodeSpecNtro nodeSpec) {
		
		// FIXME: we should be able to access the task state
		//        according to the current trace

		if(node.task().isBlocked(trace)) {

			nodeSpec.setColor("red");

		} else if(node.task().isInProgress(trace)) {

			nodeSpec.setColor("yellow");

		} else if(node.task().isDone(trace)) {

			nodeSpec.setColor("green");

		} 

	}

}
