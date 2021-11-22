package aiguilleur_server;

import ca.aiguilleur.backend.AiguilleurBackend;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import ca.ntro.app.App;
import ca.ntro.app.backend.BackendRegistrar;
import ca.ntro.core.static_imports.NtroJdk;

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
