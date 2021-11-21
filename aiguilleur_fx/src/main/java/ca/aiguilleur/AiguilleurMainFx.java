package ca.aiguilleur;

import ca.ntro.core.static_imports.NtroJdk;
import ca.ntro.tmp.ControllerRegistrar;
import ca.ntro.tmp.MessageRegistrar;
import ca.ntro.tmp.ModelRegistrar;
import ntro_app_fx.NtroAppFx;

public class AiguilleurMainFx extends AiguilleurMain implements NtroAppFx {
	
	public static void main(String[] args) throws Throwable {
		NtroJdk.initializer().executeBlocking();

	}


}
