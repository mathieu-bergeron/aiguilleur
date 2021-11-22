package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;

public interface ViewCreator<V extends View> {
	
	V createView();

}
