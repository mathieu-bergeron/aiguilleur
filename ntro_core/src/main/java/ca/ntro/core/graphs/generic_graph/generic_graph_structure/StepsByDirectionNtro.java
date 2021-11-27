package ca.ntro.core.graphs.generic_graph.generic_graph_structure;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.core.graphs.Direction;
import ca.ntro.core.graphs.Edge;
import ca.ntro.core.graphs.EdgeValue;
import ca.ntro.core.graphs.Node;
import ca.ntro.core.graphs.NodeValue;
import ca.ntro.core.graphs.WalkedStep;

public class        StepsByDirectionNtro <NV extends NodeValue, 
                                          EV extends EdgeValue,
                                          N extends Node<NV>,
                                          E extends Edge<EV>> 

       implements   StepsByDirection<NV,EV,N,E> {
	
	private Map<String, StepsInDirection<NV,EV,N,E>> steps = new HashMap<>();

	public Map<String, StepsInDirection<NV, EV, N, E>> getSteps() {
		return steps;
	}

	public void setSteps(Map<String, StepsInDirection<NV, EV, N, E>> steps) {
		this.steps = steps;
	}

	@Override
	public void memorize(WalkedStep<NV, EV, N, E> walkedStep) {

		StepsInDirection<NV,EV,N,E> stepsInDirection = steps.get(walkedStep.direction().name());
		
		if(stepsInDirection == null) {
			stepsInDirection = createStepsInDirection(walkedStep.direction());
			steps.put(walkedStep.direction().name(), stepsInDirection);
		}
		
		stepsInDirection.memorize(walkedStep);
	}

	private StepsInDirection<NV, EV, N, E> createStepsInDirection(Direction direction) {

		StepsInDirection<NV,EV,N,E> stepsInDirection = null;
		
		if(direction == Direction.FORWARD) {
			
			stepsInDirection = new StepsForwardNtro<>();
			
		}else if(direction == Direction.BACKWARD) {

			stepsInDirection = new StepsBackwardNtro<>();
			
		}else if(direction == Direction.UP) {

			stepsInDirection = new StepsUpNtro<>();

		}else if(direction == Direction.DOWN) {

			stepsInDirection = new StepsDownNtro<>();
		}
		
		return stepsInDirection;
	}

	@Override
	public boolean contains(WalkedStep<NV, EV, N, E> walkedStep) {
		boolean contains = false;
		
		StepsInDirection<NV,EV,N,E> stepsInDirection = steps.get(walkedStep.direction().name());
		
		if(stepsInDirection != null) {
			contains = stepsInDirection.contains(walkedStep);
		}
		
		return contains;
	}
	
	
}
