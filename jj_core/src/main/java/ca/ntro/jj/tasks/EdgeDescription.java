package ca.ntro.jj.tasks;

public interface EdgeDescription {
	
	NodeDescription getFrom();
	NodeDescription getTo();
	
	void write(GraphWriter writer);
}
