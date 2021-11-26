package ca.ntro.core.graphs;

import ca.ntro.core.path.Filepath;
import ca.ntro.core.path.Path;

public class WalkedStepNtro<NV extends NodeValue, EV extends EdgeValue> implements WalkedStep<NV,EV> {
	
	private Direction direction;
	private Node<NV> from;
	private Edge<EV> edge;
	private Node<NV> to;
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Node<NV> getFrom() {
		return from;
	}

	public void setFrom(Node<NV> from) {
		this.from = from;
	}

	public Edge<EV> getEdge() {
		return edge;
	}

	public void setEdge(Edge<EV> edge) {
		this.edge = edge;
	}

	public Node<NV> getTo() {
		return to;
	}

	public void setTo(Node<NV> to) {
		this.to = to;
	}

	public WalkedStepNtro(Direction direction, Node<NV> from, Node<NV> to) {
		setDirection(direction);
		setFrom(from);
		setTo(to);
	}

	public WalkedStepNtro(Direction direction, Node<NV> from, Edge<EV> edge, Node<NV> to) {
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
	public Node<NV> from() {
		return getFrom();
	}

	@Override
	public Edge<EV> edge() {
		return getEdge();
	}

	@Override
	public Node<NV> to() {
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
