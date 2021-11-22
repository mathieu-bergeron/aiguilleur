package ca.ntro.app.frontend;

public interface ViewRegistrarAccessor<RV extends RootView, V extends NtroView> {
	
	RV rootView();
	V view(Class<? extends NtroView> viewClass);


}
