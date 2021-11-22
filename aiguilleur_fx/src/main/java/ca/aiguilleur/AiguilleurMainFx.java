package ca.aiguilleur;

import ca.aiguilleur.AiguilleurApp;
import ca.aiguilleur.backend.AiguilleurRemoteBackend;
import ca.aiguilleur.frontend.AiguilleurFrontendFx;
import ca.ntro.app.App;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrar;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.core.static_imports.NtroJdk;
import ntro_app_fx.NtroAppFx;

public class AiguilleurMainFx extends AiguilleurApp<ViewRegistrarFx> implements NtroAppFx {

	public static void main(String[] args) throws Throwable {
		NtroJdk.initializer().executeBlocking();
		
		App.launch(args);
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
