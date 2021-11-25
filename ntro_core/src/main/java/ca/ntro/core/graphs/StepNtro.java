package ca.ntro.core.graphs;

import ca.ntro.core.path.PathName;

public class StepNtro implements Step {
	
	private Direction direction;
	private PathName name;
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public PathName getName() {
		return name;
	}

	public void setName(PathName name) {
		this.name = name;
	}

	public StepNtro(Direction direction, PathName name) {
		setDirection(direction);
		setName(name);
	}

	public StepNtro(Direction direction, String name) {
		setDirection(direction);
		setName(new PathName(name));
	}

	@Override
	public Direction direction() {
		return getDirection();
	}

	@Override
	public PathName name() {
		return getName();
	}
}
