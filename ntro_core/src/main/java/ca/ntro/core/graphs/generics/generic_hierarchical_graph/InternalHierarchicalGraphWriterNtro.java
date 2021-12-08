package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Direction;
import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriterNtro;
import ca.ntro.core.graphs.generics.generic_graph.NodeNotFoundException;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsNtro;
import ca.ntro.core.graphs.writers.ClusterAlreadyAddedException;
import ca.ntro.core.graphs.writers.ClusterNotFoundException;
import ca.ntro.core.graphs.writers.ClusterSpecNtro;
import ca.ntro.core.graphs.writers.EdgeSpecNtro;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.GraphWriterException;
import ca.ntro.core.graphs.writers.NodeAlreadyAddedException;
import ca.ntro.core.graphs.writers.NodeSpecNtro;
import ca.ntro.core.initialization.Ntro;

public class      InternalHierarchicalGraphWriterNtro<N extends GenericHierarchicalNode<N,E,SO>,
                                                      E extends Edge<N,E,SO>,
													  SO extends HierarchicalSearchOptionsBuilder>

       extends    InternalGraphWriterNtro<N,E,SO>

       implements InternalHierarchicalGraphWriter<N,E,SO> {

	@Override
	protected void writeNodes(GenericGraph<N,E,SO> graph, GraphWriter writer) {
		SO downOptions = graph.defaultSearchOptions();
		
		SearchOptionsNtro downOptionsNtro = new SearchOptionsNtro();
		downOptionsNtro.setDirections(new Direction[] {Direction.DOWN});

		downOptions.copyOptions(downOptionsNtro);

		// write clusters and subclusters first
		graph.forEachNode(n -> {
			if(n.hasSubNodes() && !n.hasParent()) {
				try {

					writer.addCluster(new ClusterSpecNtro(n));

				} catch (GraphWriterException e) {
					Ntro.exceptionThrower().throwException(e);
				}
				
				n.forEachSubNode((walked, subNode) -> {
					if(subNode.hasSubNodes() && subNode.hasParent()) {
						try {
							writer.addSubCluster(new ClusterSpecNtro(subNode.parent()), new ClusterSpecNtro(subNode));
						} catch (GraphWriterException e) {
							Ntro.exceptionThrower().throwException(e);
						}
					}
				});
			}
		});

		// write subnodes once subclusters are written
		graph.forEachNode(n -> {
			if(!n.hasSubNodes() && n.hasParent()) {
				try {

					writer.addSubNode(new ClusterSpecNtro(n.parent()), new NodeSpecNtro(n));

				} catch (GraphWriterException e) {
					Ntro.exceptionThrower().throwException(e);
				}
			}
		});

		graph.forEachNode(n -> {
			if(!n.hasSubNodes() && !n.hasParent()) {
				try {
					writer.addNode(new NodeSpecNtro(n));
				} catch (GraphWriterException e) {
					Ntro.exceptionThrower().throwException(e);
				}
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

		} catch (GraphWriterException e) {
			Ntro.exceptionThrower().throwException(e);
		}
	}

}
