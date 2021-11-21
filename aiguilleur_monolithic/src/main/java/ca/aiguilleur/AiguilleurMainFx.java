package ca.aiguilleur;

import ca.ntro.app.ViewRegistrar;
import ca.ntro.core.static_imports.NtroJdk;
import ntro_app_fx.NtroAppFx;

public class AiguilleurMainFx extends AiguilleurMain implements NtroAppFx {
	
	public static void main(String[] args) throws Throwable {
		NtroJdk.initializer().executeBlocking();

	}

	@Override
	public void registerViews(ViewRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}
}
