package ca.ntro.app.frontend;

import ca.ntro.app.views.FxView;

public interface FxViewRegistrar extends ViewRegistrar<FxView> {
	
	void registerRootView(String fxmlPath, int width, int height);

	void registerView(Class<? extends NtroView> viewClass, String fxmlPath);

}
