package ca.aiguilleur.backend;

import ca.aiguilleur.messages.MsgAddAppointment;
import ca.aiguilleur.models.PongModel;
import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.backend.LocalBackend;
import ca.ntro.app.backend.handlers.HandlerCreator;
import static ca.ntro.app.backend.handlers.HandlerCreator.*;

public class AiguilleurLocalBackend implements LocalBackend {

	@Override
	public void registerHandlers(HandlerCreator creator) {

		ceciEstUnTest(creator);

	}
	
	

	private void ceciEstUnTest(HandlerCreator creator) {

		creator.to("Ceci est un test")
		       .withModel(QueueModel.class)
		       .withModel(PongModel.class)
		       .when(msgReceived(MsgAddAppointment.class))
		       .execute(results -> {
		    	   
		    	   // XXX: We have a lock on these models!
		    	   QueueModel queue = results.getModel(QueueModel.class);
		    	   PongModel pong = results.getModel(PongModel.class);
		    	   
		    	   MsgAddAppointment message = results.getMessage(MsgAddAppointment.class);
		    	   
		    	   // Do something
		    	   
		    	   
		    	   // XXX: manually generate a ModelUpdate (for now)
		    	   
		       });
	}


}
