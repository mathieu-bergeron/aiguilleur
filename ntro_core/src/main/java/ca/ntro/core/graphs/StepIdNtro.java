package ca.ntro.core.graphs;

import ca.ntro.core.identifyers.Key;

public class StepIdNtro implements StepId {
	
	private Direction direction;
	private Key name;
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Key getName() {
		return name;
	}

	public void setName(Key name) {
		this.name = name;
	}

	public StepIdNtro(Direction direction, Key name) {
		setDirection(direction);
		setName(name);
	}

	public StepIdNtro(Direction direction, String name) {
		setDirection(direction);
		setName(new Key(name));
	}

	@Override
	public Direction direction() {
		return getDirection();
	}

	@Override
	public Key name() {
		return getName();
	}
}
