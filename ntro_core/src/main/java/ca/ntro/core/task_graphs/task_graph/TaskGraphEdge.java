package ca.ntro.core.task_graphs.task_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.hierarchical_dag.HierarchicalDagSearchOptions;

public interface TaskGraphEdge <T   extends Task<T,IT,AT,IAT>, 
					            IT  extends ImmutableTask<IT,AT,IAT>,
                                AT  extends AtomicTask<AT,IAT>,
                                IAT extends ImmutableAtomicTask<IAT>,
                                IG  extends ImmutableTaskGraph<T,IT,AT,IAT,IG>,
                                G   extends TaskGraph<T,IT,AT,IAT,IG,G>>

       extends Edge<TaskGraphNode<T,IT,AT,IAT,IG,G>, 
                    TaskGraphEdge<T,IT,AT,IAT,IG,G>, 
                    HierarchicalDagSearchOptions> {

}
