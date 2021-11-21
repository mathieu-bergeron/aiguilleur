package ca.aiguilleur;

import ca.aiguilleur.backend.AiguilleurBackend;
import ca.ntro.app.AppRegistrar;
import ca.ntro.core.static_imports.NtroJdk;
import ca.ntro.tmp.NtroApp;

public class AiguilleurMainMonolithic implements NtroApp {

	public static void main(String[] args) throws Throwable {
		NtroJdk.initializer().executeBlocking();
		
		launch(args);
	}

	@Override
	public void registerApp(AppRegistrar registrar) {

		registrar.registerFrontend(new AiguilleurFrontendFx());

		registrar.registerBackend(new AiguilleurBackend());
	}

}
