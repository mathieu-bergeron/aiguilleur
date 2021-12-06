package ca.ntro.core.graphs.generics.hierarchical_directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Direction;
import ca.ntro.core.graphs.generics.directed_graph.Edge;
import ca.ntro.core.graphs.generics.directed_graph.GenericDirectedGraph;
import ca.ntro.core.graphs.generics.directed_graph.InternalGraphWriterNtro;
import ca.ntro.core.graphs.generics.directed_graph.NodeNotFoundException;
import ca.ntro.core.graphs.generics.directed_graph.SearchOptionsNtro;
import ca.ntro.core.graphs.writers.ClusterNotFoundException;
import ca.ntro.core.graphs.writers.ClusterSpecNtro;
import ca.ntro.core.graphs.writers.EdgeSpecNtro;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.NodeSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class      InternalHierarchicalGraphWriterNtro<N extends HierarchicalNode<N,E,SO>,
                                                      E extends Edge<N,E,SO>,
													  SO extends HierarchicalGraphSearchOptionsBuilder>

       extends    InternalGraphWriterNtro<N,E,SO>

       implements InternalHierarchicalGraphWriter<N,E,SO> {

	@Override
	protected void writeNodes(GenericDirectedGraph<N,E,SO> graph, GraphWriter writer) {
		graph.forEachNode(n -> {
			if(n.hasSubNodes() && !n.hasParent()) {
				writer.addCluster(new ClusterSpecNtro(n));
				
				n.forEachSubNode((walked, subNode) -> {

					if(subNode.hasSubNodes() && subNode.hasParent()) {
						writer.addSubCluster(new ClusterSpecNtro(subNode.parent()), new ClusterSpecNtro(subNode));
					}

					else if(!subNode.hasSubNodes() && subNode.hasParent()) {
						writer.addSubNode(new ClusterSpecNtro(subNode.parent()), new NodeSpecNtro(subNode));
					}
				});
			}
		});

		graph.forEachNode(n -> {
			if(!n.hasSubNodes() && !n.hasParent()) {
				writer.addNode(new NodeSpecNtro(n));
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
