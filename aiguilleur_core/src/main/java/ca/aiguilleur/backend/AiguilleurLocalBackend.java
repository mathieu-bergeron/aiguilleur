package ca.aiguilleur.backend;

import ca.aiguilleur.messages.MsgAddAppointment;
import ca.aiguilleur.models.PongModel;
import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.backend.LocalBackend;
import ca.ntro.app.backend.handlers.BackendTasks;
import static ca.ntro.app.backend.handlers.BackendTasks.*;

public class AiguilleurLocalBackend implements LocalBackend {

	@Override
	public void createTasks(BackendTasks inOrder) {

		addAppointment(inOrder);
	}
	
	

	private void addAppointment(BackendTasks inOrder) {

		inOrder.to("addAppointment")

		       .execute((results, notifyer) -> {
		    	   
		    	   // XXX: We have a lock on these models!
		    	   QueueModel queue = results.getModel(QueueModel.class);
		    	   PongModel pong = results.getModel(PongModel.class);
		    	   
		    	   MsgAddAppointment message = results.getMessage(MsgAddAppointment.class);
		    	   
		    	   // Do something
		    	   
		    	   
		    	   // XXX: manually generate a ModelUpdate (for now)
		    	   
		       })

		       .when(msgReceived(MsgAddAppointment.class))

		       .and(modelLoaded(QueueModel.class))

		       .and(modelLoaded(PongModel.class))
		       
		       .onCancel(() -> {
		    	   
		    	   
		       })
		       
		       .onFailure(exception -> {
		    	   
		    	   
		       });
	}


}
