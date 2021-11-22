package ca.ntro.tmp;

import ca.ntro.app.NtroApp;

public interface NtroAppWeb extends NtroApp {
	
	void registerViews(ViewRegistrarWeb registrar);
	void registerRouters(RouterRegistrar registrar);

}
