package ca.ntro.core.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.generics.generic_graph.GraphId;
import ca.ntro.core.graphs.writers.ClusterAlreadyAddedException;
import ca.ntro.core.graphs.writers.ClusterNotFoundException;
import ca.ntro.core.graphs.writers.ClusterSpec;
import ca.ntro.core.graphs.writers.EdgeSpec;
import ca.ntro.core.graphs.writers.GraphWriter;
import ca.ntro.core.graphs.writers.GraphWriterOptions;
import ca.ntro.core.graphs.writers.NodeAlreadyAddedException;
import ca.ntro.core.graphs.writers.NodeNotFoundException;
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
	private GraphWriterOptions options;

	public GraphWriterJdk() {
	}

	@Override
	public void initialize(GraphId id, GraphWriterOptions options) {
		this.options = options;
		this.basepath = id.toFilepath();

		graph = mutGraph(basepath.filename()).setDirected(options.isDirected())
				.graphAttrs().add(Rank.dir(RankDir.LEFT_TO_RIGHT))
				.graphAttrs().add("compound", "true");
	}
	
	private File createFile(String extension) {
		File file = new File("_storage/" + basepath.toRawPath() + extension);
		
		File parentFile = file.getParentFile();

		if(parentFile != null) {
			parentFile.mkdirs();
		}
		
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
	public void addNode(NodeSpec nodeSpec) throws NodeAlreadyAddedException {
		MutableNode node = createNode(nodeSpec);
		graph.add(node);
	}

	@Override
	public void addCluster(ClusterSpec clusterSpec) throws ClusterAlreadyAddedException {
		MutableGraph cluster = createCluster(clusterSpec);

		graph.add(cluster);
	}

	@Override
	public void addSubCluster(ClusterSpec clusterSpec, ClusterSpec subClusterSpec) throws ClusterNotFoundException, ClusterAlreadyAddedException {
		MutableGraph cluster = findCluster(clusterSpec);
		MutableGraph subCluster = createCluster(subClusterSpec);
		
		cluster.add(subCluster);
	}

	@Override
	public void addSubNode(ClusterSpec clusterSpec, NodeSpec nodeSpec) throws ClusterNotFoundException, NodeAlreadyAddedException {
		MutableGraph cluster = findCluster(clusterSpec);
		MutableNode subNode = createNode(nodeSpec);

		cluster.add(subNode);
	}


	@Override
	public void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) throws NodeNotFoundException {
		MutableNode fromNode = findNode(fromSpec);
		MutableNode toNode = findNode(toSpec);

		Link link = Link.to(toNode);
		link.attrs().add("label", edgeSpec.label());

		fromNode.links().add(link);
		fromNode.attrs().add("label", fromSpec.label());
	}

	@Override
	public void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException {
		MutableGraph fromCluster = findCluster(fromSpec);
		MutableNode toNode = findNode(toSpec);

		MutableNode fromInvisibleNode = findClusterInvisibleNode(fromCluster);

		Link link = Link.to(toNode);
		link.attrs().add("label",edgeSpec.label());
		link.attrs().add("ltail","cluster_" + fromCluster.name());

		fromInvisibleNode.links().add(link);
	}

	@Override
	public void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException {
		MutableNode fromNode = findNode(fromSpec);
		MutableGraph toCluster = findCluster(toSpec);

		MutableNode toInvisibleNode = findClusterInvisibleNode(toCluster);

		Link link = Link.to(toInvisibleNode);
		link.attrs().add("lhead","cluster_" + toCluster.name());
		link.attrs().add("label",edgeSpec.label());

		fromNode.links().add(link);
		fromNode.attrs().add("label", fromSpec.label());
	}

	@Override
	public void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws ClusterNotFoundException {
		MutableGraph fromCluster = findCluster(fromSpec);
		MutableGraph toCluster = findCluster(toSpec);
	
		MutableNode toInvisibleNode = findClusterInvisibleNode(toCluster);

		Link link = Link.to(toInvisibleNode);
		link.attrs().add("lhead","cluster_" + toCluster.name());
		link.attrs().add("label",edgeSpec.label());

		MutableNode fromInvisibleNode = findClusterInvisibleNode(fromCluster);

		link.attrs().add("ltail","cluster_" + fromCluster.name());

		fromInvisibleNode.links().add(link);
	}

	private MutableGraph createCluster(ClusterSpec clusterSpec) throws ClusterAlreadyAddedException {
		if(clusters.containsKey(clusterSpec.id())) {
			throw new ClusterAlreadyAddedException("Cluster already added: " + clusterSpec.id());
		}
		
		MutableGraph cluster = mutGraph(clusterSpec.id());
		cluster.setCluster(true);
		cluster.graphAttrs().add(Rank.dir(RankDir.LEFT_TO_RIGHT));
		cluster.graphAttrs().add(Label.of(clusterSpec.label()));
		cluster.setDirected(this.options.isDirected());
		
		createClusterInvisibleNode(cluster);
		
		clusters.put(clusterSpec.id(), cluster);
		
		return cluster;
	}

	private MutableGraph findCluster(ClusterSpec clusterSpec) throws ClusterNotFoundException {
		if(!clusters.containsKey(clusterSpec.id())) {
			throw new ClusterNotFoundException("Cluster not found: " + clusterSpec.id());
		}

		return clusters.get(clusterSpec.id());
	}

	private MutableNode findClusterInvisibleNode(MutableGraph cluster) {
		return clusterInvisibleNodes.get(cluster.name().toString());
	}

	private void createClusterInvisibleNode(MutableGraph cluster) {
		if(clusterInvisibleNodes.containsKey(cluster.name().toString())) return;

		String clusterInvisibleNodeId = "__" + cluster.name() + "__";
		MutableNode clusterInvisibleNode = mutNode(clusterInvisibleNodeId);
		clusterInvisibleNode.attrs().add("shape", "none");
		clusterInvisibleNode.attrs().add("style", "invis");
		clusterInvisibleNode.attrs().add("label", "");
		
		cluster.add(clusterInvisibleNode);
		clusterInvisibleNodes.put(cluster.name().toString(), clusterInvisibleNode);
	}

	private MutableNode findNode(NodeSpec nodeSpec) throws NodeNotFoundException {
		if(!nodes.containsKey(nodeSpec.id())) {
			throw new NodeNotFoundException("Node not found: " + nodeSpec.id());
		}
		
		return nodes.get(nodeSpec.id());
	}

	private MutableNode createNode(NodeSpec nodeSpec) throws NodeAlreadyAddedException {
		if(nodes.containsKey(nodeSpec.id())) {
			throw new NodeAlreadyAddedException("Node already added: " + nodeSpec.id());
		}

		MutableNode node = mutNode(nodeSpec.id());
		
		node.attrs().add(Label.of(nodeSpec.label()));
		
		if(nodeSpec.color() != null) {
			node.attrs().add("style", "filled");
			node.attrs().add("fillcolor", nodeSpec.color());
		}

		if(nodeSpec.shape() != null) {
			node.attrs().add("shape", nodeSpec.shape());
		}
		
		nodes.put(nodeSpec.id(), node);
		
		return node;
	}
}
