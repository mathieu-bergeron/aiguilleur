package ca.ntro.core.reflection.object_graph;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.SearchOptionsNtro;

public class       ObjectGraphSearchOptionsNtro 
       extends     SearchOptionsNtro
       implements  ObjectGraphSearchOptions {

	public ObjectGraphSearchOptionsNtro(Direction[] directions) {
		super(directions);
	}

}
