package ca.ntro.core.graphs.directed_graph;

import ca.ntro.core.graphs.generics.directed_graph.Direction;
import ca.ntro.core.graphs.graph.GraphSearchOptionsBuilderNtro;
import ca.ntro.core.initialization.Ntro;

public class DirectedGraphSearchOptionsBuilderNtro 

       extends GraphSearchOptionsBuilderNtro
       
       implements DirectedGraphSearchOptionsBuilder {

	@Override
	public void setDirections(DirectedGraphDirection[] directions) {
		Direction[] translatedDirections = new Direction[directions.length];
		
		if(directions.length > 2) {
			Ntro.exceptionThrower().throwException(new IllegalArgumentException("At most 2 directions for a (nonhierarchical) directed graph"));
		}

		for(int i = 0; i < directions.length; i++) {

			if(directions[i] == DirectedGraphDirection.BACKWARD) {

				translatedDirections[i] = Direction.BACKWARD;

			}else {

				translatedDirections[i] = Direction.FORWARD;
			}
		}
		
		getSearchOptions().setDirections(translatedDirections);
	}
}
