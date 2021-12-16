package ca.ntro.core.task_graphs.task_graph_writer;

import ca.ntro.core.graph_writer.NodeSpecNtro;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagWriterOptions;
import ca.ntro.core.graphs.hierarchical_dag.InternalHierarchicalDagWriterNtro;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraphEdge;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNode;

public class InternalTaskGraphWriterNtro<T  extends Task<T,AT>, 
                                         AT extends AtomicTask<T,AT>>

       extends InternalHierarchicalDagWriterNtro<TaskGraphNode<T,AT>,
	                                             TaskGraphEdge<T,AT>>

	   implements InternalTaskGraphWriter<T,AT> {

	@Override
	protected void adjustNodeSpecAttributes(TaskGraphNode<T,AT> node,
			                                HierarchicalDagWriterOptions options,
			                                NodeSpecNtro nodeSpec) {

		//  FIXME: should ask the node for its attribute

		if(node.task().isBlocked()) {

			nodeSpec.setColor("red");

		}else if(node.task().isInProgress()) {
			
			nodeSpec.setColor("yellow");
			
		}else if(node.task().isDone()) {
			
			nodeSpec.setColor("green");
			
		}
	}
}