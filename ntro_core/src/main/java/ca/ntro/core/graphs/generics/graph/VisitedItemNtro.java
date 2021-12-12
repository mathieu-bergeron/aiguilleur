package ca.ntro.core.graphs.generics.graph;

public class VisitedItemNtro<N extends GenericNode<N,E,SO>, 
                             E extends GenericEdge<N,E,SO>,
                             SO extends SearchOptions> 

	   implements VisitedItem<N,E,SO> {

	private WalkNtro<N,E,SO> walked;

	public WalkNtro<N, E, SO> getWalked() {
		return walked;
	}

	public void setWalked(WalkNtro<N, E, SO> walked) {
		this.walked = walked;
	}

	public VisitedItemNtro() {
	}
	
	public VisitedItemNtro(WalkNtro<N,E,SO> walked) {
		setWalked(walked);
	}
	
	@Override
	public Walk<N, E, SO> walked() {
		return getWalked();
	}
}
