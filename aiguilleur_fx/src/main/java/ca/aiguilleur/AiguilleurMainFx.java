package ca.aiguilleur;

import ca.aiguilleur.backend.AiguilleurRemoteBackend;
import ca.aiguilleur.frontend.AiguilleurFrontendFx;
import ca.ntro.app.NtroAppFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrar;
import ca.ntro.app.frontend.ViewRegistrarFx;

public class AiguilleurMainFx extends AiguilleurApp<ViewRegistrarFx> implements NtroAppFx {

	public static void main(String[] args) {
		NtroAppFx.launch(args);
	}

	@Override
	public void registerFrontend(FrontendRegistrar<ViewRegistrarFx> registrar) {
		registrar.registerFrontend(new AiguilleurFrontendFx());
	}

	@Override
	public void registerBackend(BackendRegistrar registrar) {
		registrar.registerBackend(new AiguilleurRemoteBackend());
	}

}
