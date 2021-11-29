package ca.ntro.core.graphs;

import ca.ntro.core.identifyers.Name;

public class EdgeTypeNtro implements EdgeType {
	
	private Direction direction;
	private Name name;

	protected Direction getDirection() {
		return direction;
	}

	protected void setDirection(Direction direction) {
		this.direction = direction;
	}

	protected Name getName() {
		return name;
	}

	protected void setName(Name name) {
		this.name = name;
	}
	
	public EdgeTypeNtro(Direction direction, Name name) {
		setDirection(direction);
		setName(name);
	}

	public EdgeTypeNtro(Direction direction, String name) {
		setDirection(direction);
		setName(new Name(name));
	}

	@Override
	public String label() {
		return name().toString();
	}

	@Override
	public Direction direction() {
		return getDirection();
	}

	@Override
	public Name name() {
		return getName();
	}

}
