package aiguilleur_server;

import ca.aiguilleur.AiguilleurApp;
import ca.aiguilleur.backend.AiguilleurLocalBackend;
import ca.ntro.app.App;
import ca.ntro.app.NtroAppServer;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.app.frontend.FrontendRegistrarNull;
import ca.ntro.core.static_imports.NtroJdk;

public class AiguilleurMainServer extends AiguilleurApp<FrontendRegistrarNull> implements NtroAppServer {

	public static void main(String[] args) throws Throwable {
		NtroJdk.initializer().executeBlocking();
		
		App.launch(args);
	}

	@Override
	public void registerFrontend(FrontendRegistrarNull registrar) {
		// no frontend
	}

	@Override
	public void registerBackend(BackendRegistrar registrar) {
		registrar.registerBackend(new AiguilleurLocalBackend());
	}
}
