package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.InternalGraphWriterNtro;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;
import ca.ntro.core.graphs.graph_writer.ClusterSpecNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.GraphWriterException;
import ca.ntro.core.initialization.Ntro;

public class      InternalHierarchicalGraphWriterNtro<N extends GenericHierarchicalNode<N,E,SO>,
                                                      E extends GenericEdge<N,E,SO>,
													  SO extends HierarchicalSearchOptions,
													  GO extends HierarchicalGraphWriterOptions>

       extends    InternalGraphWriterNtro<N,E,SO,GO>

       implements InternalHierarchicalGraphWriter<N,E,SO,GO> {
	
	protected ClusterSpecNtro clusterSpec(GenericHierarchicalNode<N,E,SO> node) {
		ClusterSpecNtro clusterSpec = new ClusterSpecNtro(node);
		
		adjustNodeSpecAttributes(node, clusterSpec);
		
		return clusterSpec;
	}

	@Override
	protected void writeNodes(GenericGraph<N,E,SO,GO> graph, GraphWriter writer) {
		SO downOptions = graph.defaultSearchOptions();
		
		InternalSearchOptionsNtro downOptionsNtro = new InternalSearchOptionsNtro();
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
