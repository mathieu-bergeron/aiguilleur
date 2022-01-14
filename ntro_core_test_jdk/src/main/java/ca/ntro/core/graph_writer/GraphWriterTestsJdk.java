package ca.ntro.core.graph_writer;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.ntro.core.graphs.graph_writer.GraphWriterTests;
import ca.ntro.core.initialization.InitializerTestJdk;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Rank.RankDir;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import static guru.nidi.graphviz.model.Factory.*;

public class GraphWriterTestsJdk extends GraphWriterTests {

	@BeforeClass
	public static void initializeJdk() {
		InitializerTestJdk.initialize();
	}

	private File createFile(String filename) {
		File file = new File("_storage/graphs/" + filename);
		
		File parentFile = file.getParentFile();

		if(parentFile != null) {
			parentFile.mkdirs();
		}
		
		return file;
	}
	
	public void writeGraph(MutableGraph graph, String graphName) throws IOException {
		Graphviz.fromGraph(graph).render(Format.PNG).toFile(createFile(graphName + ".png"));
		Graphviz.fromGraph(graph).render(Format.DOT).toFile(createFile(graphName + ".dot"));
	}
	
	@Test
	public void graphViz01() throws IOException {

		MutableGraph graph;

		graph = mutGraph("graphViz01").setDirected(true)
				.graphAttrs().add(Rank.dir(RankDir.LEFT_TO_RIGHT))
				.graphAttrs().add("compound", "true");
		
		MutableNode nodeA = mutNode("A");
		MutableNode nodeB = mutNode("B");
		MutableNode nodeC = mutNode("C");

		graph.add(nodeA);
		graph.add(nodeB);
		graph.add(nodeC);
		
		nodeA.attrs().add("shape","record");
		nodeA.attrs().add("label","<a>a|<b>b|<c>c");

		nodeB.attrs().add("shape","record");
		nodeB.attrs().add("label","<a>a|<b>b|<c>c");

		nodeC.attrs().add("shape","record");
		nodeC.attrs().add("label","<a>a|<b>b|<c>c");
		
		nodeA.links().add(between(port("a"), nodeB.port("b")));
		nodeB.links().add(between(port("b"), nodeC.port("c")));
		
		writeGraph(graph, "graphViz01");
	}

}
