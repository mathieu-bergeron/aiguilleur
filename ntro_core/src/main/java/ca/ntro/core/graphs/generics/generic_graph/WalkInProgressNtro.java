package ca.ntro.core.graphs.generics.generic_graph;

public class      WalkInProgressNtro<N extends Node<N,E,SO>, 
                                     E extends Edge<N,E,SO>,
                                     SO extends SearchOptionsBuilder> 

       extends    VisitedItemNtro<N,E,SO>

       implements WalkInProgress<N,E,SO> {
	
	private WalkId remainingWalk;
	private N currentNode;

	public WalkId getRemainingWalk() {
		return remainingWalk;
	}

	public void setRemainingWalk(WalkId remainingWalk) {
		this.remainingWalk = remainingWalk;
	}

	public N getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(N currentNode) {
		this.currentNode = currentNode;
	}
	
	public WalkInProgressNtro() {
	}

	public WalkInProgressNtro(Walk<N,E,SO> walked, WalkId remainingWalk, N currentNode) {
		super(walked);
		setRemainingWalk(remainingWalk);
		setCurrentNode(currentNode);
	}

	@Override
	public WalkId remainingWalk() {
		return getRemainingWalk();
	}

	@Override
	public N currentNode() {
		return getCurrentNode();
	}
}
