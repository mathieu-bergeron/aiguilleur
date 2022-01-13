package ca.ntro.app.services;

import ca.ntro.app.frontend.View;

public interface Window {

	void resize(int width, int height);
	void installRootView(View<?> view);
	void show();
	void fullscreen(boolean isFullScreen);

}
