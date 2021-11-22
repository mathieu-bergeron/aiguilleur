package ca.ntro.app.frontend.controllers.tasks;

import ca.ntro.app.frontend.View;
import ca.ntro.app.models.ModelUpdates;
import ca.ntro.core.identifyers.ModelId;

public interface TaskResults {

	<V extends View> V getView(Class<V> viewClass);

	ModelUpdates getModelUpdates(ModelId id);

}
