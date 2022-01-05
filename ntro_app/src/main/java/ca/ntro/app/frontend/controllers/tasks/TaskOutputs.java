package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;

public interface TaskOutputs {
	
	static Object windowIsShown() {
		return null;
	}

	<V extends View> void registerView(Class<V> viewClass, V view);
	void notifyViewDisplayed(View view);
	

	FrontendTasks inOrder();
	void notifyWindowDisplayed();

	void notifyDisplayed(Object object);

	void addResult(Object rootView);
	
	

}
