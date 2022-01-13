package ca.ntro.app.services;

import ca.ntro.app.frontend.View;

public class WindowNull implements Window {

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void registerRootView(View view) {
	}

	@Override
	public void show() {
	}

	@Override
	public void fullscreen(boolean isFullScreen) {
	}

}
