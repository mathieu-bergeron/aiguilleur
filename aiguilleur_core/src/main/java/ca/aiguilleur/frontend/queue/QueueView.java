package ca.aiguilleur.frontend.queue;

import ca.ntro.app.frontend.View;
import ca.ntro.app.models.ModelUpdate;

public interface QueueView extends View {

	void displayModelUpdate(ModelUpdate update);

}
