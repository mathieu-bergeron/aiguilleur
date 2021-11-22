package ca.ntro.app.frontend;

public interface ViewRegistrarAccessor<RV extends RootView, V extends View> {
	
	RV rootView();
	V view(Class<? extends View> viewClass);


}
