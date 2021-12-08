package ca.ntro.core.graphs.common;

import ca.ntro.core.identifyers.Name;
import ca.ntro.core.path.Path;

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

	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(o instanceof EdgeType) {
			EdgeType e = (EdgeType) o;

			if(e.direction() == null && direction != null) {
				return false;
			}
			
			if(e.direction() != null && !e.direction().equals(direction)) {
				return false;
			}
			
			if(e.name() == null && name != null) {
				return false;
			}
			
			if(e.name() != null && !e.name().equals(name)) {
				return false;
			}

			return true;
		}
		
		return false;
	}
	
	
	
	
	public EdgeTypeNtro(Direction direction, Name name) {
		setDirection(direction);
		setName(name);
	}

	public EdgeTypeNtro(Direction direction, String name) {
		setDirection(direction);
		setName(new Name(name));
	}

	public EdgeTypeNtro() {
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
	
	
	@Override
	public String toString() {
		Path path = Path.fromSingleName(direction().name());
		path.addName(name().toString());

		return path.toRawPath();
	}

	@Override
	public boolean equalsUndirected(EdgeType other) {
		if(other.name() == null && name() != null) {
			return false;
		}
		
		if(other.name() != null && !other.name().equals(other.name())) {
			return false;
		}

		if(other.direction() == null && direction() != null) {
			return false;
		}

		if(other.direction() != null && !other.direction().equalsUndirected(other.direction())) {
			return false;
		}
		
		return true;
	}

}
