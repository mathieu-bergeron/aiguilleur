package ca.aiguilleur;

import ca.aiguilleur.AiguilleurApp;
import ca.aiguilleur.backend.AiguilleurRemoteBackend;
import ca.aiguilleur.frontend.AiguilleurFrontendFx;
import ca.ntro.app.NtroAppFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrar;
import ca.ntro.app.frontend.FxViewRegistrar;

public class AiguilleurMainFx extends AiguilleurApp<FxViewRegistrar> implements NtroAppFx {

	public static void main(String[] args) {
		NtroAppFx.launch(args);
	}

	@Override
	public void registerFrontend(FrontendRegistrar<FxViewRegistrar> registrar) {
		registrar.registerFrontend(new AiguilleurFrontendFx());
	}

	@Override
	public void registerBackend(BackendRegistrar registrar) {
		registrar.registerBackend(new AiguilleurRemoteBackend());
	}

}
