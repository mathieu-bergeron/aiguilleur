package ca.ntro.app.frontend;


public interface Frontend<VR extends ViewRegistrar<?>> {

	void registerViews(VR registrar);

	void registerControllers(ControllerRegistrar registrar);

}
