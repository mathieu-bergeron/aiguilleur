package ca.ntro.app.models;

import ca.ntro.app.frontend.View;

public interface MutationDisplayer<M extends ModelMutation,
                                   V extends View> {
	
	void displayMutation(M mutation, V view);

}
