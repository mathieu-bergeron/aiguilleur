package ca.ntro.core.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptions;
import ca.ntro.core.graphs.graph_writer.ClusterAlreadyAddedException;
import ca.ntro.core.graphs.graph_writer.ClusterNotFoundException;
import ca.ntro.core.graphs.graph_writer.ClusterSpec;
import ca.ntro.core.graphs.graph_writer.EdgeSpec;
import ca.ntro.core.graphs.graph_writer.GraphItemSpec;
import ca.ntro.core.graphs.graph_writer.GraphWriter;
import ca.ntro.core.graphs.graph_writer.NodeAlreadyAddedException;
import ca.ntro.core.graphs.graph_writer.NodeNotFoundException;
import ca.ntro.core.graphs.graph_writer.NodeSpec;
import ca.ntro.core.graphs.graph_writer.StructureSpec;
import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.path.Filepath;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Records;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Link;
import guru.nidi.graphviz.model.MutableAttributed;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import guru.nidi.graphviz.model.Port;

import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.attribute.Records.*;

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
		checkThatNodeExists(fromSpec);
		checkThatNodeExists(toSpec);
		
		// XXX: all edges are written to the top-level graph
		MutableNode toNode = mutNode(toSpec.id());
		MutableNode fromNode = mutNode(fromSpec.id());

		// FIXME: nodeSpec should have a port attribute
		Link test = Link.to(toNode.port("port01"));
		
		Link link = Link.to(toNode);
		link.attrs().add("label", edgeSpec.label());

		fromNode.links().add(link);
		fromNode.attrs().add("label", fromSpec.label());

		graph.add(fromNode);
	}

	@Override
	public void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, NodeSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException {
		checkThatClusterExists(fromSpec);
		checkThatNodeExists(toSpec);
		
		MutableNode fromNode = mutNode(invisibleNodeId(fromSpec));
		MutableNode toNode = mutNode(toSpec.id());

		Link link = Link.to(toNode);
		link.attrs().add("label",edgeSpec.label());
		link.attrs().add("ltail","cluster_" + fromSpec.id());

		fromNode.links().add(link);
		
		graph.add(fromNode);
	}

	@Override
	public void addEdge(NodeSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws NodeNotFoundException, ClusterNotFoundException {
		checkThatNodeExists(fromSpec);
		checkThatClusterExists(toSpec);

		MutableNode fromNode = mutNode(fromSpec.id());
		MutableNode toNode = mutNode(invisibleNodeId(toSpec));

		Link link = Link.to(toNode);
		link.attrs().add("lhead","cluster_" + toSpec.id());
		link.attrs().add("label",edgeSpec.label());

		fromNode.links().add(link);
		fromNode.attrs().add("label", fromSpec.label());
		
		graph.add(fromNode);
	}

	@Override
	public void addEdge(ClusterSpec fromSpec, EdgeSpec edgeSpec, ClusterSpec toSpec) throws ClusterNotFoundException {
		checkThatClusterExists(fromSpec);
		checkThatClusterExists(toSpec);

		MutableNode fromNode = mutNode(invisibleNodeId(fromSpec));
		MutableNode toNode = mutNode(invisibleNodeId(toSpec));
	
		Link link = Link.to(toNode);
		link.attrs().add("lhead","cluster_" + toSpec.id());
		link.attrs().add("label",edgeSpec.label());

		link.attrs().add("ltail","cluster_" + fromSpec.id());

		fromNode.links().add(link);

		graph.add(fromNode);
	}
	
	private void checkThatClusterDoesNotAlreadyExists(ClusterSpec clusterSpec) throws ClusterAlreadyAddedException {
		if(clusters.containsKey(clusterSpec.id())) {
			throw new ClusterAlreadyAddedException("Cluster already added: " + clusterSpec.id());
		}
	}

	private MutableGraph createCluster(ClusterSpec clusterSpec) throws ClusterAlreadyAddedException {
		checkThatClusterDoesNotAlreadyExists(clusterSpec);
		
		MutableGraph cluster = mutGraph(clusterSpec.id());
		cluster.setCluster(true);
		cluster.graphAttrs().add(Rank.dir(RankDir.LEFT_TO_RIGHT));
		cluster.setDirected(this.options.isDirected());
		
		adjustNodeAttributes(cluster.graphAttrs(), clusterSpec);
		
		MutableNode invisibleNode = createClusterInvisibleNode(clusterSpec);
		cluster.add(invisibleNode);
		
		clusters.put(clusterSpec.id(), cluster);
		
		return cluster;
	}
	
	private void checkThatClusterExists(ClusterSpec clusterSpec) throws ClusterNotFoundException {
		if(!clusters.containsKey(clusterSpec.id())) {
			throw new ClusterNotFoundException("Cluster not found: " + clusterSpec.id());
		}
	}

	private MutableGraph findCluster(ClusterSpec clusterSpec) throws ClusterNotFoundException {
		checkThatClusterExists(clusterSpec);

		return clusters.get(clusterSpec.id());
	}
	
	private String invisibleNodeId(ClusterSpec clusterSpec) {
		return "__" + clusterSpec.id() + "__";
	}

	private MutableNode createClusterInvisibleNode(ClusterSpec clusterSpec) {
		if(clusterInvisibleNodes.containsKey(clusterSpec.id())) {
			return clusterInvisibleNodes.get(clusterSpec.id());
		}

		String invisibleNodeId = invisibleNodeId(clusterSpec);

		MutableNode invisibleNode = mutNode(invisibleNodeId);
		invisibleNode.attrs().add("shape", "none");
		invisibleNode.attrs().add("style", "invis");
		invisibleNode.attrs().add("label", "");
		
		clusterInvisibleNodes.put(clusterSpec.id(), invisibleNode);
		
		return invisibleNode;
	}
	
	private void checkThatNodeExists(NodeSpec nodeSpec) throws NodeNotFoundException {
		if(!nodes.containsKey(nodeSpec.id())) {
			throw new NodeNotFoundException("Node not found: " + nodeSpec.id());
		}
	}

	private void checkThatNodeDoesNotAlreadyExists(NodeSpec nodeSpec) throws NodeAlreadyAddedException {
		if(nodes.containsKey(nodeSpec.id())) {
			throw new NodeAlreadyAddedException("Node already added: " + nodeSpec.id());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void adjustGraphItemAttributes(MutableAttributed attrs, GraphItemSpec spec) {
		attrs.add(Label.of(spec.label()));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void adjustNodeAttributes(MutableAttributed attrs, NodeSpec spec) {

		adjustGraphItemAttributes(attrs, spec);
		
		// FIXME: if node is a structure
		if(spec instanceof StructureSpec) {
			attrs.add(Records.of(turn(rec("port01", "value01"),rec("port01", "value02"))));
		}
		
		attrs.add("style", "filled");
		if(spec.color() != null) {
			attrs.add("fillcolor", spec.color());
		}else {
			attrs.add("fillcolor", "white");
		}

		if(spec.shape() != null) {
			attrs.add("shape", spec.shape());
		}
	}
	
	private MutableNode createNode(NodeSpec nodeSpec) throws NodeAlreadyAddedException {
		checkThatNodeDoesNotAlreadyExists(nodeSpec);
		
		
		MutableNode node = mutNode(nodeSpec.id());

		adjustNodeAttributes(node.attrs(), nodeSpec);
		
		nodes.put(nodeSpec.id(), node);
		
		
		return node;
	}
}
