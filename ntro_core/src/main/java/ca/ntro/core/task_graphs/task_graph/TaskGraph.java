package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.values.ObjectMap;

public interface TaskGraph<T  extends Task<T,AT>, 
                           AT extends AtomicTask<T,AT>> {
	
	void setGraphName(String graphName);

	T findTask(TaskId id);
	T findTask(String id);

	AT findAtomicTask(AtomicTaskId id);
	AT findAtomicTask(String id);

	T addTask(TaskId id);
	T addTask(String id);
	
	Stream<T> tasks();
	
	boolean hasCurrent();    // the results queue has at least one complete ObjectMap (for the whole graph)
	ObjectMap current();     

	boolean hasNext();       // the results queue has at least a subsequent complete ObjectMap (for the whole graph)
	ObjectMap next();
	
	
	

	void write(GraphWriter writer);

	static <T extends Task<T,AT>, AT extends AtomicTask<T,AT>> TaskGraph<T,AT> newGraph(Class<T> taskClass, Class<AT> atomicTaskClass) {
		
		TaskGraphNtro<T,AT> graph = new TaskGraphNtro<>();
		
		graph.setTaskClass(taskClass);
		graph.setAtomicTaskClass(atomicTaskClass);
		
		graph.initialize();
		
		return graph;
	}
}
