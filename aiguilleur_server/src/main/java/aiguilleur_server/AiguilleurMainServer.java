package aiguilleur_server;

import ca.aiguilleur.backend.AiguilleurBackend;
import ca.ntro.app.BackendRegistrar;
import ca.ntro.app.App;
import ca.ntro.core.static_imports.NtroJdk;
import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;

public class AiguilleurMainServer implements App {

	public static void main(String[] args) throws Throwable {
		NtroJdk.initializer().executeBlocking();
		
		App.launch(args);
	}

	@Override
	public void registerModels(ModelRegistrar registrar) {
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
	}

	@Override
	public void registerApp(BackendRegistrar registrar) {
		
		registrar.registerBackend(new AiguilleurBackend());
	}


}
