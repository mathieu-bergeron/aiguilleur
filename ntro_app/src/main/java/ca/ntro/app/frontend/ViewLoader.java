package ca.ntro.app.frontend;

public interface ViewLoader<V extends View> {
	
	V createView();

}
