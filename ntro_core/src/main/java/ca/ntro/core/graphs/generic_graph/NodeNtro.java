package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeId;
import ca.ntro.core.graphs.NodeValue;

public class NodeNtro<NV extends NodeValue> implements Node<NV> {
	
	private NodeId id;
	private NV value;

	public NodeNtro(NodeId id, NV value) {
		this.id = id;
		this.value = value;
	}

	@Override
	public NodeId id() {
		return id;
	}

	@Override
	public NV value() {
		return value;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof NodeNtro) {
			NodeNtro n = (NodeNtro) o;
			
			if(!(n.id == null ? id == null : n.id.equals(id))) {
				return false;
			}

			if(!(n.value == null ? value == null : n.value.equals(value))) {
				return false;
			}
			
			return true;
		}
		
		
		return false;
	}

}
