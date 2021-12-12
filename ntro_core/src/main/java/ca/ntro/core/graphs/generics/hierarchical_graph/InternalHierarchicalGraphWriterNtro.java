package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriterNtro;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;
import ca.ntro.core.graphs.graph_writer.ClusterSpecNtro;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.GraphWriterException;
import ca.ntro.core.initialization.Ntro;

public class      InternalHierarchicalGraphWriterNtro<N extends GenericHierarchicalNode<N,E,SO>,
                                                      E extends GenericEdge<N,E,SO>,
													  SO extends HierarchicalSearchOptions,
													  GO extends HierarchicalGraphWriterOptions>

       extends    GenericInternalGraphWriterNtro<N,E,SO,GO>

       implements InternalHierarchicalGraphWriter<N,E,SO,GO> {
	
	protected ClusterSpecNtro clusterSpec(GenericHierarchicalNode<N,E,SO> node, GO options) {
		ClusterSpecNtro clusterSpec = new ClusterSpecNtro(node);
		
		adjustNodeSpecAttributes(node, options, clusterSpec);
		
		return clusterSpec;
	}

	@Override
	protected void writeNodes(GenericGraph<N,E,SO,GO> graph, GO options, GraphWriter writer) {
		SO downOptions = graph.defaultSearchOptions();
		
		InternalSearchOptionsNtro downOptionsNtro = new InternalSearchOptionsNtro();
		downOptionsNtro.setDirections(new Direction[] {Direction.DOWN});

		downOptions.copyOptions(downOptionsNtro);
		
		graph.nodes().forEach(n -> {

			try {

				if(n.hasSubNodes() && !n.hasParent()) {

					writer.addCluster(clusterSpec(n, options));
					
					n.subNodes().forEach(visitedSubNode -> {
						
						N subNode = visitedSubNode.node();

						if(subNode.hasSubNodes() && subNode.hasParent()) {

							writer.addSubCluster(clusterSpec(subNode.parent(), options), clusterSpec(subNode, options));

						} else if(!subNode.hasSubNodes() && subNode.hasParent()) {

							writer.addSubNode(clusterSpec(subNode.parent(), options), nodeSpec(subNode, options));
						}
					});

				}else if(!n.hasSubNodes() && !n.hasParent()){

					writer.addNode(nodeSpec(n, options));
					
				}

			}catch(GraphWriterException e) {

				Ntro.exceptionThrower().throwException(e);

			}
		});
	}

	@Override
	protected void writeEdge(GraphWriter writer, GO options, E edge) {
		if(edge.type().direction() == Direction.DOWN
				|| edge.type().direction() == Direction.UP) {
			return;
		}

		try {

			if(edge.from().hasSubNodes()
					&& edge.to().hasSubNodes()) {
				
				writer.addEdge(clusterSpec(edge.from(), options), edgeSpec(edge, options), clusterSpec(edge.to(), options));
				
			}else if(edge.from().hasSubNodes()) {

				writer.addEdge(clusterSpec(edge.from(), options), edgeSpec(edge, options), nodeSpec(edge.to(), options));
				
			}else if(edge.to().hasSubNodes()) {

				writer.addEdge(nodeSpec(edge.from(), options), edgeSpec(edge, options), clusterSpec(edge.to(), options));
				
			}else {

				writer.addEdge(nodeSpec(edge.from(), options), edgeSpec(edge, options), nodeSpec(edge.to(), options));

			}

		} catch (GraphWriterException e) {
			Ntro.exceptionThrower().throwException(e);
		}
	}

}
