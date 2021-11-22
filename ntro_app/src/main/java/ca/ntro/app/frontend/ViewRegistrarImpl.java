package ca.ntro.app.frontend;

public interface ViewRegistrarImpl<RV extends NtroRootView, V extends NtroView> {
	
	RV rootView();
	V view(Class<? extends NtroView> viewClass);


}
