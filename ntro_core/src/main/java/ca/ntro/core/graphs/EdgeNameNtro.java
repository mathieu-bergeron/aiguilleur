package ca.ntro.core.graphs;

import ca.ntro.core.identifyers.Name;

public class EdgeNameNtro implements EdgeName {
	
	private Name name; 
	private Direction direction;
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public EdgeNameNtro(Direction direction, Name name) {
		setDirection(direction);
		setName(name);
	}

	public EdgeNameNtro(Direction direction, String name) {
		setDirection(direction);
		setName(new Name(name));
	}

	@Override
	public String label() {
		return getName().toString();
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
