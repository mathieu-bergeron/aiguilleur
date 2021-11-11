package ca.ntro.tmp;


public interface NtroAppWeb extends NtroApp {
	
	void registerViews(ViewRegistrarWeb registrar);
	void registerRouters(RouterRegistrar registrar);

}
