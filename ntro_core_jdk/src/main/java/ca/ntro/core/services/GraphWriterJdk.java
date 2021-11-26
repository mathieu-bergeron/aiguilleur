package ca.ntro.core.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.GraphId;
import ca.ntro.core.graphs.NodeNotFoundException;
import ca.ntro.core.graphs.writers.ClusterNotFoundException;
import ca.ntro.core.graphs.writers.ClusterSpec;
import ca.ntro.core.graphs.writers.EdgeSpec;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.GraphWriterOptions;
import ca.ntro.core.graphs.writers.NodeSpec;
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
	public void addCluster(ClusterSpec clusterSpec) {
		MutableGraph cluster = createCluster(clusterSpec);
		clusters.put(clusterSpec.id(), cluster);
		graph.add(cluster);
	}

	@Override
	public void addSubCluster(ClusterSpec cluster, ClusterSpec subCluster) {
		MutableGraph dotCluster = clusters.get(cluster.id());
		dotCluster.add(createCluster(subCluster));
	}

	@Override
	public void addSubNode(ClusterSpec cluster, NodeSpec subNode) {
		MutableGraph dotCluster = clusters.get(cluster.id());
		dotCluster.add(createNode(subNode));
	}

	@Override
	public void addNode(NodeSpec nodeSpec) {
		MutableNode node = createNode(nodeSpec);
		nodes.put(nodeSpec.id(), node);
		graph.add(node);
	}

	@Override
	public void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) throws NodeNotFoundException {
		MutableNode fromNode = nodes.get(fromSpec.id());
		MutableNode toNode = nodes.get(toSpec.id());

		if(fromNode == null) {
			throw new NodeNotFoundException("Node not found " + fromSpec.id());
		}

		if(toNode == null) {
			throw new NodeNotFoundException("Node not found " + toSpec.id());
		}

		Link link = Link.to(toNode);
		link.attrs().add("label", edgeSpec.label());

		fromNode.links().add(link);
		fromNode.attrs().add("label", fromSpec.label());
	}

	@Override
	public void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException {
		MutableGraph fromCluster = clusters.get(fromSpec.id());
		MutableNode toNode = nodes.get(toSpec.id());

		if(fromCluster == null) {
			throw new ClusterNotFoundException("Cluster not found " + fromSpec.id());
		}

		if(toNode == null) {
			throw new NodeNotFoundException("Node not found " + toSpec.id());
		}

		createClusterInvisibleNode(fromCluster);
		MutableNode fromInvisibleNode = clusterInvisibleNodes.get(fromSpec.id());
		MutableNode fromNode = mutNode(fromInvisibleNode.name());

		Link link = Link.to(toNode);
		link.attrs().add("label",edgeSpec.label());
		link.attrs().add("ltail","cluster_" + fromCluster.name());

		fromNode.links().add(link);
		fromNode.attrs().add("label", fromSpec.label());

		graph.add(fromNode);
	}

	@Override
	public void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException {
		MutableNode fromNode = nodes.get(fromSpec.id());
		MutableGraph toCluster = clusters.get(toSpec.id());
		
		if(fromNode == null) {
			throw new NodeNotFoundException("Node not found " + fromSpec.id());
		}

		if(toCluster == null) {
			throw new ClusterNotFoundException("Cluster not found " + toSpec.id());
		}

		createClusterInvisibleNode(toCluster);
		MutableNode toInvisibleNode = clusterInvisibleNodes.get(toSpec.id());

		Link link = Link.to(toInvisibleNode);
		link.attrs().add("lhead","cluster_" + toCluster.name());
		link.attrs().add("label",edgeSpec.label());

		fromNode.links().add(link);
		fromNode.attrs().add("label", fromSpec.label());
	}

	@Override
	public void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws ClusterNotFoundException {
		MutableGraph fromCluster = clusters.get(fromSpec.id());
		MutableGraph toCluster = clusters.get(toSpec.id());
		
		if(fromCluster == null) {
			throw new ClusterNotFoundException("Cluster not found " + fromSpec.id());
		}

		if(toCluster == null) {
			throw new ClusterNotFoundException("Cluster not found " + toSpec.id());
		}

		createClusterInvisibleNode(toCluster);
		MutableNode toInvisibleNode = clusterInvisibleNodes.get(toSpec.id());

		Link link = Link.to(toInvisibleNode);
		link.attrs().add("lhead","cluster_" + toCluster.name());
		link.attrs().add("label",edgeSpec.label());

		createClusterInvisibleNode(fromCluster);
		MutableNode fromInvisibleNode = clusterInvisibleNodes.get(fromSpec.id());

		MutableNode fromNode = mutNode(fromInvisibleNode.name());
		link.attrs().add("ltail","cluster_" + fromCluster.name());

		fromNode.links().add(link);
		fromNode.attrs().add("label", fromSpec.label());

		graph.add(fromNode);
	}

	private MutableGraph createCluster(ClusterSpec clusterSpec) {
		if(clusters.containsKey(clusterSpec.id())) return clusters.get(clusterSpec.id());
		
		MutableGraph cluster = mutGraph(clusterSpec.id());
		cluster.setCluster(true);
		cluster.graphAttrs().add(Rank.dir(RankDir.LEFT_TO_RIGHT));
		cluster.graphAttrs().add(Label.of(clusterSpec.label()));
		cluster.setDirected(true);

		clusters.put(clusterSpec.id(), cluster);

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

	private MutableNode createNode(NodeSpec nodeSpec) {
		if(nodes.containsKey(nodeSpec.id())) return nodes.get(nodeSpec.id());

		MutableNode node = mutNode(nodeSpec.id());
		node.attrs().add(Label.of(nodeSpec.label()));
		nodes.put(nodeSpec.id(), node);
		return node;
	}

}
