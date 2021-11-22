package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;

public interface TaskResults {

	<V extends View> V getView(Class<V> viewClass);

}
