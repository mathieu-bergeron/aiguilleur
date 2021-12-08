package ca.ntro.core.graphs.generics.generic_hierarchical_graph;

import ca.ntro.core.graphs.generics.generic_graph.Direction;
import ca.ntro.core.graphs.generics.generic_graph.Edge;
import ca.ntro.core.graphs.generics.generic_graph.GenericGraph;
import ca.ntro.core.graphs.generics.generic_graph.InternalGraphWriterNtro;
import ca.ntro.core.graphs.generics.generic_graph.SearchOptionsNtro;
import ca.ntro.core.graphs.graph_writer.ClusterSpecNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.GraphWriterException;
import ca.ntro.core.initialization.Ntro;

public class      InternalHierarchicalGraphWriterNtro<N extends GenericHierarchicalNode<N,E,SO>,
                                                      E extends Edge<N,E,SO>,
													  SO extends HierarchicalSearchOptionsBuilder>

       extends    InternalGraphWriterNtro<N,E,SO>

       implements InternalHierarchicalGraphWriter<N,E,SO> {
	
	protected ClusterSpecNtro clusterSpec(GenericHierarchicalNode<N,E,SO> node) {
		ClusterSpecNtro clusterSpec = new ClusterSpecNtro(node);
		
		adjustNodeSpecAttributes(node, clusterSpec);
		
		return clusterSpec;
	}

	@Override
	protected void writeNodes(GenericGraph<N,E,SO> graph, GraphWriter writer) {
		SO downOptions = graph.defaultSearchOptions();
		
		SearchOptionsNtro downOptionsNtro = new SearchOptionsNtro();
		downOptionsNtro.setDirections(new Direction[] {Direction.DOWN});

		downOptions.copyOptions(downOptionsNtro);
		
		graph.forEachNode(n -> {

			try {

				if(n.hasSubNodes() && !n.hasParent()) {

					writer.addCluster(clusterSpec(n));
					
					n.forEachSubNode((walked, subNode) -> {

						if(subNode.hasSubNodes() && subNode.hasParent()) {

							writer.addSubCluster(clusterSpec(subNode.parent()), clusterSpec(subNode));

						} else if(!subNode.hasSubNodes() && subNode.hasParent()) {

							writer.addSubNode(clusterSpec(subNode.parent()), nodeSpec(subNode));
						}
					});

				}else if(!n.hasSubNodes() && !n.hasParent()){

					writer.addNode(nodeSpec(n));
					
				}

			}catch(GraphWriterException e) {

				Ntro.exceptionThrower().throwException(e);

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
				
				writer.addEdge(clusterSpec(edge.from()), edgeSpec(edge), clusterSpec(edge.to()));
				
			}else if(edge.from().hasSubNodes()) {

				writer.addEdge(clusterSpec(edge.from()), edgeSpec(edge), nodeSpec(edge.to()));
				
			}else if(edge.to().hasSubNodes()) {

				writer.addEdge(nodeSpec(edge.from()), edgeSpec(edge), clusterSpec(edge.to()));
				
			}else {

				writer.addEdge(nodeSpec(edge.from()), edgeSpec(edge), nodeSpec(edge.to()));

			}

		} catch (GraphWriterException e) {
			Ntro.exceptionThrower().throwException(e);
		}
	}

}
