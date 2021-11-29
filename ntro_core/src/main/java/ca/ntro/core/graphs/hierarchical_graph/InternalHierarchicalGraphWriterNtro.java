package ca.ntro.core.graphs.hierarchical_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.NodeNotFoundException;
import ca.ntro.core.graphs.SearchOptions;
import ca.ntro.core.graphs.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generic_graph.InternalGraphWriterNtro;
import ca.ntro.core.graphs.writers.ClusterNotFoundException;
import ca.ntro.core.graphs.writers.ClusterSpecNtro;
import ca.ntro.core.graphs.writers.EdgeSpecNtro;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.NodeSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class      InternalHierarchicalGraphWriterNtro<N extends HierarchicalNode<N,E,SO>,
                                                      E extends Edge<N,E,SO>,
													  SO extends SearchOptions>

       extends    InternalGraphWriterNtro<N,E,SO>

       implements InternalHierarchicalGraphWriter<N,E,SO> {

	@Override
	protected void writeAfterInitialization(GenericGraph<N,E,SO> graph, GraphWriter writer) {

		writeClusters(graph, writer);

		super.writeAfterInitialization(graph, writer);
	}

	private void writeClusters(GenericGraph<N,E,SO> graph, GraphWriter writer) {
		graph.forEachNode(n -> {

			if(n.hasSubNodes() && !n.hasParent()) {
				writer.addCluster(new ClusterSpecNtro(n));
			}

			else if(n.hasSubNodes() && n.hasParent()) {
				writer.addSubCluster(new ClusterSpecNtro(n.parent()), new ClusterSpecNtro(n));
			}

			else if(!n.hasSubNodes() && n.hasParent()) {
				writer.addSubNode(new ClusterSpecNtro(n.parent()), new NodeSpecNtro(n));
			}
		});
	}

	@Override
	protected void writeEdge(GraphWriter writer, E edge) {
		if(edge.type().direction() == Direction.DOWN
				|| edge.type().direction() == Direction.UP) {
			return;
		}

		try {

			if(edge.from().hasSubNodes()
					&& edge.to().hasSubNodes()) {
				
				writer.addEdge(new ClusterSpecNtro(edge.from()), new EdgeSpecNtro(edge), new ClusterSpecNtro(edge.to()));
				
			}else if(edge.from().hasSubNodes()) {

				writer.addEdge(new ClusterSpecNtro(edge.from()), new EdgeSpecNtro(edge), new NodeSpecNtro(edge.to()));
				
			}else if(edge.to().hasSubNodes()) {

				writer.addEdge(new NodeSpecNtro(edge.from()), new EdgeSpecNtro(edge), new ClusterSpecNtro(edge.to()));
				
			}else {

				writer.addEdge(new NodeSpecNtro(edge.from()), new EdgeSpecNtro(edge), new NodeSpecNtro(edge.to()));

			}

		} catch (NodeNotFoundException | ClusterNotFoundException e) {

			Ntro.exceptionThrower().throwException(e);
		}
	}

}
