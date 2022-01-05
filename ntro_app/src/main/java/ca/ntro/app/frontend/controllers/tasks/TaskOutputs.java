package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;

public interface TaskOutputs {

	<V extends View> void registerView(Class<V> viewClass, V view);
	void notifyViewDisplayed(View view);
	
	FrontendTasks inOrder();

	
	

}
