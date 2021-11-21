package ca.aiguilleur;

import ca.ntro.core.static_imports.NtroJdk;
import ntro_app_fx.NtroAppFx;

public class AiguilleurMainMonolithic extends AiguilleurMainFx implements NtroAppFx {
	
	public static void main(String[] args) throws Throwable {
		NtroJdk.initializer().executeBlocking();
		
		// TODO: register BOTH frontend and backend, then launch
	}

}
