package ca.ntro.core;


public interface NtroAppWeb extends NtroApp {
	
	void registerViews(ViewRegistrarWeb registrar);
	void registerRouters(RouterRegistrar registrar);

}
