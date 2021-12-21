package ca.aiguilleur.model_mutations;

import ca.ntro.app.models.MutationDisplayer;
import ca.aiguilleur.frontend.queue.QueueView;
import ca.ntro.app.models.ModelMutation;

public class AddAppointment 

       implements ModelMutation,
                  MutationDisplayer<AddAppointment, QueueView> {

	@Override
	public void displayMutation(AddAppointment mutation, QueueView view) {
		
	}

}
