package ca.aiguilleur.backend;

import ca.aiguilleur.messages.MsgAddAppointment;
import ca.aiguilleur.models.PongModel;
import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.backend.LocalBackend;
import ca.ntro.app.backend.handlers.BackendTaskCreator;
import static ca.ntro.app.backend.handlers.BackendTaskCreator.*;

public class AiguilleurLocalBackend implements LocalBackend {

	@Override
	public void createTasks(BackendTaskCreator to) {

		addAppointment(to);
	}
	
	

	private void addAppointment(BackendTaskCreator to) {

		to.execute("addAppointment")

		  .waitFor(message(MsgAddAppointment.class))

		  .waitFor(model(QueueModel.class))

		  .waitFor(model(PongModel.class))

		  .thenDo((inputs, notify) -> {
		    	   
		    	   // XXX: We have a lock on these models!
		    	   QueueModel queue = inputs.get(model(QueueModel.class));
		    	   PongModel pong = inputs.get(model(PongModel.class));
		    	   
		    	   MsgAddAppointment message = inputs.get(message(MsgAddAppointment.class));
		    	   
		    	   // Do something
		    	   
		    	   
		    	   // XXX: manually generate a ModelUpdate (for now)
		    	   
		    	   notify.notDoneYet();
		       })
		       
		       .onCancelDo(() -> {
		    	   
		    	   
		       })
		       
		       .onFailureDo(exception -> {
		    	   
		    	   
		       });
	}


}
