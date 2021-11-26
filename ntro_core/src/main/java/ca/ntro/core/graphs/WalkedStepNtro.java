package ca.ntro.core.graphs;

import ca.ntro.core.path.Path;
import ca.ntro.core.reflection.ObjectValue;
import ca.ntro.core.reflection.ReferenceValue;

public class WalkedStepNtro<NV extends NodeValue, 
                            EV extends EdgeValue,
                            N extends Node<NV>,
                            E extends Edge<EV>> implements WalkedStep<NV,EV,N,E> {
	
	private Direction direction;
	private N from;
	private E edge;
	private N to;
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public N getFrom() {
		return from;
	}

	public void setFrom(N from) {
		this.from = from;
	}

	public E getEdge() {
		return edge;
	}

	public void setEdge(E edge) {
		this.edge = edge;
	}

	public N getTo() {
		return to;
	}

	public void setTo(N to) {
		this.to = to;
	}

	public WalkedStepNtro(Direction direction, N from, N to) {
		setDirection(direction);
		setFrom(from);
		setTo(to);
	}

	public WalkedStepNtro(Direction direction, N from, E edge, N to) {
		setDirection(direction);
		setFrom(from);
		setEdge(edge);
		setTo(to);
	}

	@Override
	public Direction direction() {
		return getDirection();
	}

	@Override
	public N from() {
		return getFrom();
	}

	@Override
	public E edge() {
		return getEdge();
	}

	@Override
	public N to() {
		return getTo();
	}

	@Override
	public WalkedStepId id() {
		Path path = Path.fromSingleName(getDirection().name());

		path.append(Path.fromRawPath(getFrom().id().toKey()));

		if(getEdge() != null) {
			path.append(Path.fromRawPath(getEdge().id().toKey()));
		}

		path.append(Path.fromRawPath(getTo().id().toKey()));
		
		return new WalkedStepId(path);
	}

}
