package ca.ntro.app.frontend;

public interface FrontendRegistrar<VR extends ViewRegistrar> {
	
	void registerFrontend(Frontend<VR> frontend);
	

}
