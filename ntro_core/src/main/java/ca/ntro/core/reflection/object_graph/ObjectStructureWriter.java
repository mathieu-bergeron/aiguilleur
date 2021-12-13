package ca.ntro.core.reflection.object_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.core.graph_writer.EdgeSpec;
import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graph_writer.GraphWriterException;
import ca.ntro.core.graph_writer.RecordEdgeSpec;
import ca.ntro.core.graph_writer.RecordEdgeSpecNtro;
import ca.ntro.core.graph_writer.RecordItemSpecNtro;
import ca.ntro.core.graph_writer.RecordNodeSpec;
import ca.ntro.core.graph_writer.RecordNodeSpecNtro;
import ca.ntro.core.graph_writer.RecordSpecNtro;
import ca.ntro.core.graphs.generics.graph.GenericGraph;
import ca.ntro.core.initialization.Ntro;

public class ObjectStructureWriter {

	private GenericGraph<ObjectNode,ReferenceEdge,ObjectGraphSearchOptions,ObjectGraphWriterOptions> graph;
	private ObjectGraphWriterOptions options;
	private InternalObjectGraphWriterNtro internalWriter;
	private GraphWriter writer;
	private List<RecordEdgeSpec> recordEdges = new ArrayList<>();
	private Map<String, RecordNodeSpecNtro> nodeSpecByNodeId = new HashMap<>();
	private Map<String, RecordSpecNtro> recordByNodeId = new HashMap<>();

	public GenericGraph<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, ObjectGraphWriterOptions> getGraph() {
		return graph;
	}

	public void setGraph(
			GenericGraph<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, ObjectGraphWriterOptions> graph) {
		this.graph = graph;
	}

	public ObjectGraphWriterOptions getOptions() {
		return options;
	}

	public void setOptions(ObjectGraphWriterOptions options) {
		this.options = options;
	}

	public InternalObjectGraphWriterNtro getInternalWriter() {
		return internalWriter;
	}

	public void setInternalWriter(InternalObjectGraphWriterNtro internalWriter) {
		this.internalWriter = internalWriter;
	}

	public GraphWriter getWriter() {
		return writer;
	}

	public void setWriter(GraphWriter writer) {
		this.writer = writer;
	}

	public List<RecordEdgeSpec> getRecordEdges() {
		return recordEdges;
	}

	public void setRecordEdges(List<RecordEdgeSpec> recordEdges) {
		this.recordEdges = recordEdges;
	}

	public Map<String, RecordNodeSpecNtro> getNodeSpecByNodeId() {
		return nodeSpecByNodeId;
	}

	public void setNodeSpecByNodeId(Map<String, RecordNodeSpecNtro> nodeSpecByNodeId) {
		this.nodeSpecByNodeId = nodeSpecByNodeId;
	}

	public Map<String, RecordSpecNtro> getRecordByNodeId() {
		return recordByNodeId;
	}

	public void setRecordByNodeId(Map<String, RecordSpecNtro> recordByNodeId) {
		this.recordByNodeId = recordByNodeId;
	}
	
	

	public ObjectStructureWriter(GenericGraph<ObjectNode, ReferenceEdge, ObjectGraphSearchOptions, ObjectGraphWriterOptions> graph, 
			                     InternalObjectGraphWriterNtro internalWriter, 
			                     ObjectGraphWriterOptions options, GraphWriter writer) {
		setGraph(graph);
		setInternalWriter(internalWriter);
		setOptions(options);
		setWriter(writer);
	}

	public void writeNodes() {
		getGraph().visitNodes().forEach(visitedNode -> {
			
			ObjectNode node = visitedNode.node();

			processNode(node);

			node.edges().forEach(edge -> {
				
				processEdge(edge);
			});
			
			if(node.isUserDefinedObject()) {
				writeNode(node);
			}
		});
	}

	private void processNode(ObjectNode node) {
		if(node.isUserDefinedObject()
				&& !nodeSpecByNodeId.containsKey(node.id().toKey().toString())) {
			
			RecordNodeSpecNtro nodeSpec = getInternalWriter().recordNodeSpec(node, options);
			RecordSpecNtro record = nodeSpec.getRecord();
			
			nodeSpecByNodeId.put(node.id().toKey().toString(), nodeSpec);
			recordByNodeId.put(node.id().toKey().toString(), record);

			RecordItemSpecNtro nodeLabelItem = record.addItem();
			nodeLabelItem.setValue(node.label());
			nodeLabelItem.setPortName(RecordNodeSpec.MAIN_PORT_NAME);
		}
	}

	private void writeNode(ObjectNode node) {

		RecordNodeSpecNtro nodeSpec = nodeSpecByNodeId.get(node.id().toKey().toString());
		
		try {

			writer.addNode(nodeSpec);

		} catch (GraphWriterException e) {
			
			Ntro.exceptionThrower().throwException(e);
		}
	}

	private void processEdge(ReferenceEdge edge) {
		RecordNodeSpecNtro fromSpec = nodeSpecByNodeId.get(edge.from().id().toKey().toString());
		RecordSpecNtro fromRecord = recordByNodeId.get(edge.from().id().toKey().toString());

		String attributeName = edge.name().toString();

		RecordSpecNtro subRecord = fromRecord.addSubRecord();

		RecordItemSpecNtro nameItem = subRecord.addItem();
		nameItem.setValue(attributeName);

		if(edge.to().isSimpleValue()) {

			RecordItemSpecNtro valueItem = subRecord.addItem();
			valueItem.setValue(edge.to().asSimpleValue().asString());
			
		}else if(edge.to().isList()
				|| edge.to().isMap()) {
			
			RecordSpecNtro valuesRecord = subRecord.addSubRecord();

			recordByNodeId.put(edge.to().id().toKey().toString(), valuesRecord);
			nodeSpecByNodeId.put(edge.to().id().toKey().toString(), fromSpec);
			
		}else if(edge.to().isUserDefinedObject()) {
			
			RecordItemSpecNtro referenceItem = subRecord.addItem();
			referenceItem.setPortName(attributeName);

			processNode(edge.to());

			RecordNodeSpecNtro toSpec = nodeSpecByNodeId.get(edge.to().id().toKey().toString());

			getRecordEdges().add(new RecordEdgeSpecNtro(fromSpec, 
											            referenceItem.port(),
											            edge,
											            toSpec,
											            RecordNodeSpec.MAIN_PORT_NAME));
		}
	}

	public void writeEdges() {
		for(EdgeSpec edge : recordEdges) {
			try {

				writer.addEdge(edge);

			} catch (GraphWriterException e) {
				
				Ntro.exceptionThrower().throwException(e);
			}
		}
	}
}
