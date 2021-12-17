package ca.ntro.core.graphs.generics.hierarchical_graph;

import ca.ntro.core.graph_writer.EdgeSpecNtro;
import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graph_writer.GraphWriterException;
import ca.ntro.core.graphs.common.Direction;
import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.graphs.generics.graph.GenericInternalGraphWriterNtro;
import ca.ntro.core.graphs.generics.graph.InternalSearchOptionsNtro;
import ca.ntro.core.initialization.Ntro;

public class      GenericInternalHierarchicalGraphWriterNtro<N extends GenericHierarchicalNode<N,E,SO>,
                                                             E extends GenericEdge<N,E,SO>,
													         SO extends HierarchicalSearchOptions,
													         GO extends HierarchicalGraphWriterOptions>

       extends    GenericInternalGraphWriterNtro<N,E,SO,GO>

       implements InternalHierarchicalGraphWriter<N,E,SO,GO> {

	@Override
	protected void writeNodes(GenericGraph<N,E,SO,GO> graph, GO options, GraphWriter writer) {
		SO downOptions = graph.defaultSearchOptions();
		
		InternalSearchOptionsNtro downOptionsNtro = new InternalSearchOptionsNtro();
		downOptionsNtro.setDirections(new Direction[] {Direction.DOWN});

		downOptions.copyOptions(downOptionsNtro);
		
		graph.nodes().forEach(n -> {

			try {

				if(n.hasSubNodes() && !n.hasParent()) {

					writer.addCluster(nodeSpec(n, options));
					
					n.subNodes().forEach(visitedSubNode -> {
						
						N subNode = visitedSubNode.node();

						if(subNode.hasSubNodes() && subNode.hasParent()) {

							writer.addSubCluster(nodeSpec(subNode.parent(), options), nodeSpec(subNode, options));

						} else if(!subNode.hasSubNodes() && subNode.hasParent()) {

							writer.addSubNode(nodeSpec(subNode.parent(), options), nodeSpec(subNode, options));
						}
					});

				}else if(!n.hasSubNodes() && !n.hasParent()){

					writer.addNode(nodeSpec(n, options));
					
				}

			}catch(GraphWriterException e) {

				Ntro.exceptions().throwException(e);

			}
		});
	}

	@Override
	protected EdgeSpecNtro edgeSpec(GenericEdge<N,E,SO> edge, GO options) {
		return new EdgeSpecNtro(nodeSpec(edge.from(), options), edge, nodeSpec(edge.to(), options));
	}

	@Override
	protected void writeEdge(GraphWriter writer, GO options, E edge) {
		if(edge.type().direction() == Direction.DOWN
				|| edge.type().direction() == Direction.UP) {
			return;
		}

		try {
			
			writer.addEdge(edgeSpec(edge, options));

		} catch (GraphWriterException e) {
			Ntro.exceptions().throwException(e);
		}
	}

}
