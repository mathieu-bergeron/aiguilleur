package ca.aiguilleur;

import ca.aiguilleur.backend.AiguilleurRemoteBackend;
import ca.aiguilleur.frontend.AiguilleurFrontendFx;
import ca.ntro.app.NtroAppFx;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarFx;

public class AiguilleurMainFx extends AiguilleurApp<FrontendRegistrarFx> implements NtroAppFx {

	public static void main(String[] args) {
		NtroAppFx.launch(args);
	}

	@Override
	public void registerFrontend(FrontendRegistrarFx registrar) {
		registrar.registerFrontend(new AiguilleurFrontendFx());
	}

	@Override
	public void registerBackend(BackendRegistrar registrar) {
		registrar.registerBackend(new AiguilleurRemoteBackend());
	}

}
