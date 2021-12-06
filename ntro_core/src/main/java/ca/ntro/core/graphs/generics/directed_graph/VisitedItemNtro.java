package ca.ntro.core.graphs.generics.directed_graph;

public class VisitedItemNtro<N extends GenericNode<N,E,SO>, 
                             E extends Edge<N,E,SO>,
                             SO extends SearchOptionsBuilder> 

	   implements VisitedItem<N,E,SO> {

	private Walk<N,E,SO> walked;

	public Walk<N, E, SO> getWalked() {
		return walked;
	}

	public void setWalked(Walk<N, E, SO> walked) {
		this.walked = walked;
	}

	public VisitedItemNtro() {
	}
	
	public VisitedItemNtro(Walk<N,E,SO> walked) {
		setWalked(walked);
	}
	
	@Override
	public Walk<N, E, SO> walked() {
		return getWalked();
	}
}
