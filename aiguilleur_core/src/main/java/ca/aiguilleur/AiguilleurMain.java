package ca.aiguilleur;

import ca.ntro.core.initialization.Log;
import ca.ntro.core.initialization.T;
import ca.ntro.tmp.ControllerRegistrar;
import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;
import ca.ntro.tmp.NtroApp;

public class AiguilleurMain implements NtroApp  {
	
	public static void main() {
		T.trace(AiguilleurMain.class);

		Log.info("Bonjour!");
	}

	@Override
	public void registerModels(ModelRegistrar registrar) {
	}

	@Override
	public void registerMessages(MessageRegistrar registrar) {
	}

	@Override
	public void registerControllers(ControllerRegistrar registar) {
	}
}
