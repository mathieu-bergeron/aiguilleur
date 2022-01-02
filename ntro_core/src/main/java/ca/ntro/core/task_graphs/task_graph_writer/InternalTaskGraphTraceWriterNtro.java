package ca.ntro.core.task_graphs.task_graph_writer;

import ca.ntro.core.graph_writer.NodeSpecNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptions;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphNode;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;

public class InternalTaskGraphTraceWriterNtro<T  extends GenericTask<T,AT>, 
                                              AT extends GenericAtomicTask<T,AT>>

       extends InternalTaskGraphWriterNtro<T,AT>

       implements InternalTaskGraphTraceWriter<T,AT> {

	private TaskGraphTraceNtro trace;

	public TaskGraphTraceNtro getTrace() {
		return trace;
	}

	public void setTrace(TaskGraphTraceNtro trace) {
		this.trace = trace;
	}

	public InternalTaskGraphTraceWriterNtro() {
	}

	public InternalTaskGraphTraceWriterNtro(TaskGraphTraceNtro trace) {
		setTrace(trace);
	}

	@Override
	protected void adjustNodeSpecAttributes(TaskGraphNode<T,AT> node,
			                                HierarchicalDagWriterOptions options,
			                                NodeSpecNtro nodeSpec) {
		
		TaskTrace taskTrace = getTrace().getTaskTrace(node.task().id());

		if(node.task().isBlocked(taskTrace)) {

			nodeSpec.setColor("red");

		} else if(node.task().isInProgress(taskTrace)) {

			nodeSpec.setColor("yellow");

		} else if(node.task().isDone(taskTrace)) {

			nodeSpec.setColor("green");

		} 

	}

}
