package ca.ntro.app.frontend;

public class FrontendRegistrarFx implements FrontendRegistrar<ViewRegistrarFx>, FrontendRegistrarAccessor<ViewRegistrarFx> {
	
	private ViewRegistrarFx viewRegistrar = new ViewRegistrarFx();

	@Override
	public ViewRegistrarFx viewRegistrar() {
		return viewRegistrar;
	}

	@Override
	public void registerFrontend(Frontend<ViewRegistrarFx> frontend) {
		frontend.registerViews(viewRegistrar);
	}
	


}
