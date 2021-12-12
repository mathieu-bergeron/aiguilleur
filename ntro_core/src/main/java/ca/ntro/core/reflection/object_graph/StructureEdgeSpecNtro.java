package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.generics.graph.GenericEdge;
import ca.ntro.core.graphs.graph_writer.EdgeSpecNtro;
import ca.ntro.core.graphs.graph_writer.StructureSpec;

public class      StructureEdgeSpecNtro 

       extends    EdgeSpecNtro 
       
       implements StructureEdgeSpec {
	

	private StructureSpec from;
	private StructureSpec to;

	public StructureSpec getFrom() {
		return from;
	}

	public void setFrom(StructureSpec from) {
		this.from = from;
	}

	public StructureSpec getTo() {
		return to;
	}

	public void setTo(StructureSpec to) {
		this.to = to;
	}

	public StructureEdgeSpecNtro() {
		super();
	}

	public StructureEdgeSpecNtro(GenericEdge<?, ?, ?> edge, StructureSpec from, StructureSpec to) {
		super(edge);
		setFrom(from);
		setTo(to);
	}

	@Override
	public StructureSpec from() {
		return getFrom();
	}

	@Override
	public StructureSpec to() {
		return getTo();
	}
}
