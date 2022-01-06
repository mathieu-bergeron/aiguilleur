package ca.ntro.app.frontend;

public interface Window {

	void resize(int width, int height);
	void installRootView(View view);
	void show();
	
	Object rawWindow();

}
