package ca.ntro.core.graphs;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<NV> from() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Edge<EV> edge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<NV> to() {
		// TODO Auto-generated method stub
		return null;
	}

}
