package ca.ntro.core.task_graphs.generic_task_graph;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.task_graph_trace.TaskGraphTrace;

public interface GenericTaskGraph<T  extends GenericTask<T,AT>, 
                                  AT extends GenericAtomicTask<T,AT>> {
	
	void setGraphName(String graphName);

	T findTask(TaskId id);
	T findTask(String id);

	AT findAtomicTask(AtomicTaskId id);
	AT findAtomicTask(String id);

	T addTask(TaskId id);
	T addTask(String id);
	
	Stream<T> tasks();
	
	void write(GraphWriter writer);      // XXX: writing w/o an iterator only writes the structure
	
	TaskGraphTrace newTrace();

	static <T extends GenericTask<T,AT>, AT extends GenericAtomicTask<T,AT>> GenericTaskGraph<T,AT> newGenericGraph(Class<T> taskClass, Class<AT> atomicTaskClass) {
		
		GenericTaskGraphNtro<T,AT> graph = new GenericTaskGraphNtro<>();
		
		graph.setTaskClass(taskClass);
		graph.setDefaultAtomicTaskClass(atomicTaskClass);
		
		graph.initialize();
		
		return graph;
	}
}
