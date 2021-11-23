package ca.ntro.core.graphs.generic_graph;

import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeId;
import ca.ntro.core.graphs.EdgeValue;

public class EdgeNtro<EV extends EdgeValue> implements Edge<EV> {
	
	private EdgeId id;
	private EV value;

	public EdgeNtro(EdgeId id, EV value) {
		this.id = id;
		this.value = value;
	}

	@Override
	public EdgeId id() {
		return id;
	}

	@Override
	public EV value() {
		return value;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof NodeNtro) {
			EdgeNtro e = (EdgeNtro) o;
			
			if(!(e.id == null ? id == null : e.id.equals(id))) {
				return false;
			}

			if(!(e.value == null ? value == null : e.value.equals(value))) {
				return false;
			}
			
			return true;
		}
		
		
		return false;
	}

}
