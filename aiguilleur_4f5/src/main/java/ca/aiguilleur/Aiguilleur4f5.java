package ca.aiguilleur;

import ca.aiguilleur.backend.AiguilleurLocalBackend;
import ca.aiguilleur.messages.MsgAddAppointment;
import ca.aiguilleur.models.QueueModel;
import ca.ntro.app.NtroAppFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;

public class Aiguilleur4f5 implements NtroAppFx {

	public static void main(String[] args) throws Throwable {
		NtroAppFx.launch(args);
	}

	@Override
	public void registerFrontend(FrontendRegistrarFx registrar) {

		registrar.registerFrontend(new Frontend4f5());

	}

	@Override
	public void registerModels(ModelRegistrar registrar) {

		registrar.registerModelClass(QueueModel.class);

	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {

		registrar.registerMessageClass(MsgAddAppointment.class);

	}


	@Override
	public void registerBackend(BackendRegistrar registrar) {

		registrar.registerBackend(new AiguilleurLocalBackend());

	}
}
