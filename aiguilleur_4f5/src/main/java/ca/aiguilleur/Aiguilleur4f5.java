package ca.aiguilleur;

import ca.ntro.app.NtroAppFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.events.EventRegistrar;
import ca.ntro.app.frontend.FrontendRegistrar;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;

public class Aiguilleur4f5 implements NtroAppFx {

	public static void main(String[] args) throws Throwable {
		NtroAppFx.launch(args);
	}

	@Override
	public void registerFrontend(FrontendRegistrar<ViewRegistrarFx> registrar) {
	}

	@Override
	public void registerModels(ModelRegistrar registrar) {
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
	}


	@Override
	public void registerBackend(BackendRegistrar registrar) {
	}

}
