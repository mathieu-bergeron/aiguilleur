package ca.aiguilleur;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.aiguilleur.messages.MsgAddAppointment;
import ca.aiguilleur.models.PongModel;
import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.App;
import ca.ntro.app.frontend.FrontendRegistrar;

public abstract class AiguilleurApp<FR extends FrontendRegistrar<?>> implements App<FR> {

	@Override
	public void registerModels(ModelRegistrar registrar) {
		
		registrar.registerModelClass(QueueModel.class);
		registrar.registerModelClass(PongModel.class);
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {

		registrar.registerMessageClass(MsgAddAppointment.class);
	}
}
