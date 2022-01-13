package ca.ntro.app.frontend;

import ca.ntro.app.models.ModelUpdates;

public interface ViewModel<VN extends Object> extends View<VN> {

	void displayModelUpdates(ModelUpdates updates);

}
