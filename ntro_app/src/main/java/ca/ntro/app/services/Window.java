package ca.ntro.app.services;

import ca.ntro.app.frontend.View;

public interface Window {

	void resize(int width, int height);
	void registerRootView(View<?> rootView);
	void show();
	void fullscreen(boolean isFullScreen);

}
