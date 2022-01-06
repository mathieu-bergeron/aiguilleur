package ca.ntro.core.task_graphs.task_graph_writer;

import ca.ntro.core.graph_writer.NodeSpecNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptions;
import ca.ntro.core.task_graphs.generic_task_graph.GenericAtomicTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTask;
import ca.ntro.core.task_graphs.generic_task_graph.GenericTaskNtro;
import ca.ntro.core.task_graphs.generic_task_graph.TaskGraphNode;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTraceNtro;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTrace;
import ca.ntro.core.task_graphs.task_graph_trace.TaskTraceNtro;

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
		
		
		TaskTraceNtro taskTrace = (TaskTraceNtro) getTrace().getTaskTrace(node.task().id());
		
		if(taskTrace.isBlocked()) {

			nodeSpec.setColor("red");

		} else if(taskTrace.isInProgress()) {

			nodeSpec.setColor("yellow");

		} else if(taskTrace.isDone()) {

			nodeSpec.setColor("green");

		} 

		String label = node.id().toKey().toString();
		label += " [";
		label += taskTrace.getState().name();
		label += "]";

		nodeSpec.setLabel(label);
	}

}
