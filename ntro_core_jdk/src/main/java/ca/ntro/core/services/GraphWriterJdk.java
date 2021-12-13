package ca.ntro.core.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graph_writer.ClusterAlreadyAddedException;
import ca.ntro.core.graph_writer.ClusterNotFoundException;
import ca.ntro.core.graph_writer.EdgeSpec;
import ca.ntro.core.graph_writer.GraphItemSpec;
import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graph_writer.GraphWriterException;
import ca.ntro.core.graph_writer.NodeAlreadyAddedException;
import ca.ntro.core.graph_writer.NodeNotFoundException;
import ca.ntro.core.graph_writer.NodeSpec;
import ca.ntro.core.graph_writer.RecordSpec;
import ca.ntro.core.graphs.generics.graph.GraphId;
import ca.ntro.core.graphs.generics.graph.GraphWriterOptions;
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
	public void addCluster(NodeSpec clusterSpec) throws ClusterAlreadyAddedException {
		MutableGraph cluster = createCluster(clusterSpec);
		graph.add(cluster);
	}

	@Override
	public void addSubCluster(NodeSpec clusterSpec, NodeSpec subClusterSpec) throws ClusterNotFoundException, ClusterAlreadyAddedException {
		MutableGraph cluster = findCluster(clusterSpec);
		MutableGraph subCluster = createCluster(subClusterSpec);
		
		cluster.add(subCluster);
	}

	@Override
	public void addSubNode(NodeSpec clusterSpec, NodeSpec nodeSpec) throws ClusterNotFoundException, NodeAlreadyAddedException {
		MutableGraph cluster = findCluster(clusterSpec);
		MutableNode subNode = createNode(nodeSpec);

		cluster.add(subNode);
	}

	@Override
	public void addEdge(EdgeSpec edgeSpec) throws GraphWriterException {
		checkThatNodeExists(edgeSpec.from());
		checkThatNodeExists(edgeSpec.to());

		// XXX: all edges are written to the top-level graph
		MutableNode fromNode = null;
		if(edgeSpec.from().isCluster()) {
			
			fromNode = mutNode(invisibleNodeId(edgeSpec.from()));
			
		}else {

			fromNode = mutNode(edgeSpec.from().id());
		}
		
		MutableNode toNode = null; 
		if(edgeSpec.to().isCluster()) {

			toNode = mutNode(invisibleNodeId(edgeSpec.to()));
			
		}else {
			
			toNode = mutNode(edgeSpec.to().id());
		}
		
		Link link;
		
		if(edgeSpec.hasToPort()) {

			link = Link.to(toNode.port(edgeSpec.toPort()));

		}else {
			
			link = Link.to(toNode);
		}

		link.attrs().add("label", edgeSpec.label());
		
		if(edgeSpec.from().isCluster()) {
			link.attrs().add("ltail","cluster_" + edgeSpec.from().id());
		}
		
		if(edgeSpec.to().isCluster()) {
			link.attrs().add("lhead","cluster_" + edgeSpec.to().id());
		}
		
		if(edgeSpec.hasFromPort()) {

			// FIXME: c (centered) added by default
			fromNode.port(edgeSpec.fromPort()).port("c").links().add(link);
			
		}else {
			
			fromNode.links().add(link);
		}

		graph.add(fromNode);
		
	}

	
	private void checkThatClusterDoesNotAlreadyExists(NodeSpec clusterSpec) throws ClusterAlreadyAddedException {
		if(clusters.containsKey(clusterSpec.id())) {
			throw new ClusterAlreadyAddedException("Cluster already added: " + clusterSpec.id());
		}
	}

	private MutableGraph createCluster(NodeSpec clusterSpec) throws ClusterAlreadyAddedException {
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
	
	private void checkThatClusterExists(NodeSpec clusterSpec) throws ClusterNotFoundException {
		if(!clusters.containsKey(clusterSpec.id())) {
			throw new ClusterNotFoundException("Cluster not found: " + clusterSpec.id());
		}
	}

	private MutableGraph findCluster(NodeSpec clusterSpec) throws ClusterNotFoundException {
		checkThatClusterExists(clusterSpec);

		return clusters.get(clusterSpec.id());
	}
	
	private String invisibleNodeId(NodeSpec clusterSpec) {
		return "__" + clusterSpec.id() + "__";
	}

	private MutableNode createClusterInvisibleNode(NodeSpec clusterSpec) {
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
	
	private void checkThatNodeExists(NodeSpec nodeSpec) throws GraphWriterException {
		if(nodeSpec.isCluster()) {

			if(!clusters.containsKey(nodeSpec.id())) {
				throw new ClusterNotFoundException("Cluster not found: " + nodeSpec.id());
			}

		}else {
			
			if(!nodes.containsKey(nodeSpec.id())) {
				throw new NodeNotFoundException("Node not found: " + nodeSpec.id());
			}
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
	private void adjustNodeAttributes(MutableAttributed attrs, NodeSpec nodeSpec) {
		
		if(nodeSpec.isRecord()) {

			attrs.add(Records.of(labelFromRecordSpec(nodeSpec.asRecord())));
			
		}else {

			adjustGraphItemAttributes(attrs, nodeSpec);
		}

		attrs.add("style", "filled");
		if(nodeSpec.color() != null) {
			attrs.add("fillcolor", nodeSpec.color());
		}else {
			attrs.add("fillcolor", "white");
		}

		if(nodeSpec.shape() != null) {
			attrs.add("shape", nodeSpec.shape());
		}
	}
	
	private String labelFromRecordSpec(RecordSpec recordSpec) {
			return recordSpec.items().reduceToResult(new StringBuilder(), (accumulator, item) -> {
				if(accumulator.length() > 0) {
					accumulator.append("|");
				}
				
				if(item.hasPort()) {
					accumulator.append('<');
					accumulator.append(item.port());
					accumulator.append('>');
				}
				
				if(item.hasValue()) {
					accumulator.append(item.value());
				}

				if(item.isRecord()) {
					accumulator.append('{');
					accumulator.append(labelFromRecordSpec(item.asRecord()));
					accumulator.append('}');
				}

				return accumulator;

			}).value().toString();
	}
	

	private MutableNode createNode(NodeSpec nodeSpec) throws NodeAlreadyAddedException {
		checkThatNodeDoesNotAlreadyExists(nodeSpec);
		
		
		MutableNode node = mutNode(nodeSpec.id());

		adjustNodeAttributes(node.attrs(), nodeSpec);
		
		nodes.put(nodeSpec.id(), node);
		
		
		return node;
	}
}
