package ca.ntro.core.graphs.generics.graph;

public class      WalkInProgressNtro<N extends GenericNode<N,E,SO>, 
                                     E extends GenericEdge<N,E,SO>,
                                     SO extends SearchOptions> 

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

	public WalkInProgressNtro(WalkNtro<N,E,SO> walked, WalkId remainingWalk, N currentNode) {
		super(walked);
		setRemainingWalk(remainingWalk);
		setCurrentNode(currentNode);
	}

	public WalkInProgressNtro(WalkNtro<N, E, SO> walked, WalkId remainingWalk) {
		super(walked);
		setRemainingWalk(remainingWalk);
	}

	@Override
	public WalkId remainingWalk() {
		return getRemainingWalk();
	}

	@Override
	public N currentNode() {
		return getCurrentNode();
	}

	@Override
	public boolean hasCurrentNode() {
		return getCurrentNode() != null;
	}
}
