package ca.aiguilleur;

import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.aiguilleur.frontend.pong.PongModel;
import ca.aiguilleur.messages.DisplayPongMessage;
import ca.aiguilleur.messages.DisplayQueueMessage;
import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.App;
import ca.ntro.app.frontend.ViewRegistrar;

public abstract class AiguilleurApp<VR extends ViewRegistrar<?>> implements App<VR> {

	@Override
	public void registerModels(ModelRegistrar registrar) {
		
		registrar.registerModel(QueueModel.class);
		registrar.registerModel(PongModel.class);
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {

		registrar.registerMessage(DisplayQueueMessage.class);
		registrar.registerMessage(DisplayPongMessage.class);
	}
}
