package ca.ntro.core.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.GraphWriterOptions;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Filepath;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import static guru.nidi.graphviz.model.Factory.*;

public class GraphWriterJdk implements GraphWriter {
	
	private Filepath basepath;

	private MutableGraph graph;
	private Map<String, MutableGraph> clusters = new HashMap<>();
	private Map<String, MutableNode> clusterInvisibleNodes = new HashMap<>();
	private Map<String, MutableNode> nodes = new HashMap<>();

	public GraphWriterJdk() {
	}

	@Override
	public void initialize(GraphId id, GraphWriterOptions options) {
		this.basepath = id.toFilepath();

		graph = mutGraph(basepath.filename()).setDirected(options.isDirected())
				.graphAttrs().add(Rank.dir(RankDir.LEFT_TO_RIGHT))
				.graphAttrs().add("compound", "true");
	}
	
	private File createFile(String extension) {
		File file = new File("_storage/" + basepath.toRawPath() + extension);
		file.mkdirs();
		
		return file;
	}

	@Override
	public void writePng() {
		try {

			Graphviz.fromGraph(graph).render(Format.PNG).toFile(createFile(".png"));

		} catch (IOException e) {

			Ntro.exceptionThrower().throwException(e);

		}
	}


	@Override
	public void writeSvg() {
		try {

			Graphviz.fromGraph(graph).render(Format.SVG).toFile(createFile(".svg"));

		} catch (IOException e) {

			Ntro.exceptionThrower().throwException(e);

		}
		
	}


	@Override
	public void writeDot() {
		try {

			Graphviz.fromGraph(graph).render(Format.DOT).toFile(createFile(".dot"));

		} catch (IOException e) {

			Ntro.exceptionThrower().throwException(e);

		}
		
	}

	@Override
	public void addCluster(Node<? extends NodeValue> cluster) {
		MutableGraph dotCluster = createCluster(cluster);
		graph.add(dotCluster);
	}

	@Override
	public void addSubCluster(Node<? extends NodeValue> cluster, Node<? extends NodeValue> subCluster) {
		MutableGraph dotCluster = clusters.get(cluster.id().toKey());
		dotCluster.add(createCluster(subCluster));
	}

	@Override
	public void addSubNode(Node<? extends NodeValue> cluster, Node<? extends NodeValue> subNode) {
		MutableGraph dotCluster = clusters.get(cluster.id().toKey());
		dotCluster.add(createNode(subNode));
	}

	@Override
	public void addRootNode(Node<? extends NodeValue> node) {
		MutableNode dotNode = createNode(node);
		graph.add(dotNode);
	}

	@Override
	public void addEdge(Node<? extends NodeValue> from, Edge<? extends EdgeValue> edge, Node<? extends NodeValue> to) {
		MutableGraph fromCluster = clusters.get(from.id().toKey());
		MutableGraph toCluster = clusters.get(to.id().toKey());
		
		Link link = null;
		
		if(toCluster != null) {
			createClusterInvisibleNode(toCluster);
			MutableNode toInvisibleNode = clusterInvisibleNodes.get(to.id().toKey());
			link = Link.to(toInvisibleNode);
			link.attrs().add("lhead","cluster_" + toCluster.name());

		}else {
			
			MutableNode toNode = mutNode(to.id().toKey());
			toNode.attrs().add("label", to.value().label());
			graph.add(toNode);
			link = Link.to(toNode);
		}

		link.attrs().add("label",edge.value().label());

		if(fromCluster != null) {
			createClusterInvisibleNode(fromCluster);
			MutableNode fromInvisibleNode = clusterInvisibleNodes.get(from.id().toKey());

			MutableNode fromNode = mutNode(fromInvisibleNode.name());
			link.attrs().add("ltail","cluster_" + fromCluster.name());

			fromNode.links().add(link);
			fromNode.attrs().add("label", from.value().label());

			graph.add(fromNode);
			
		} else {

			MutableNode fromNode = mutNode(from.id().toKey());
			fromNode.links().add(link);
			fromNode.attrs().add("label", from.value().label());

			graph.add(fromNode);
		}
	}

	

	private MutableGraph createCluster(Node<? extends NodeValue> clusterSpec) {
		if(clusters.containsKey(clusterSpec.id().toKey())) return clusters.get(clusterSpec.id().toKey());
		
		MutableGraph cluster = mutGraph(clusterSpec.id().toKey());
		cluster.setCluster(true);
		cluster.graphAttrs().add(Rank.dir(RankDir.LEFT_TO_RIGHT));
		cluster.graphAttrs().add(Label.of(clusterSpec.value().label()));
		cluster.setDirected(true);

		clusters.put(clusterSpec.id().toKey(), cluster);

		return cluster;
	}

	private void createClusterInvisibleNode(MutableGraph cluster) {
		if(clusterInvisibleNodes.containsKey(cluster.name().toString())) return;

		String clusterInvisibleNodeId = "__" + cluster.name() + "__";
		MutableNode clusterInvisiableNode = mutNode(clusterInvisibleNodeId);
		clusterInvisiableNode.attrs().add("shape", "none");
		clusterInvisiableNode.attrs().add("style", "invis");
		clusterInvisiableNode.attrs().add("label", "");
		
		cluster.add(clusterInvisiableNode);
		clusterInvisibleNodes.put(cluster.name().toString(), clusterInvisiableNode);
	}

	private MutableNode createNode(Node<? extends NodeValue> nodeSpec) {
		if(nodes.containsKey(nodeSpec.id().toKey())) return nodes.get(nodeSpec.id().toKey());

		MutableNode node = mutNode(nodeSpec.id().toKey());
		node.attrs().add(Label.of(nodeSpec.value().label()));
		nodes.put(nodeSpec.id().toKey(), node);
		return node;
	}
}
