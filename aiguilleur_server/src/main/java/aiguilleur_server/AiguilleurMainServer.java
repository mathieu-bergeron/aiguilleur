package aiguilleur_server;

import ca.aiguilleur.backend.AiguilleurBackend;
import ca.ntro.app.AppRegistrar;
import ca.ntro.app.NtroApp;
import ca.ntro.core.static_imports.NtroJdk;
import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;

public class AiguilleurMainServer implements NtroApp {

	public static void main(String[] args) throws Throwable {
		NtroJdk.initializer().executeBlocking();
		
		NtroApp.launch(args);
	}

	@Override
	public void registerModels(ModelRegistrar registrar) {
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
	}

	@Override
	public void registerApp(AppRegistrar registrar) {
		
		registrar.registerBackend(new AiguilleurBackend());
	}


}
