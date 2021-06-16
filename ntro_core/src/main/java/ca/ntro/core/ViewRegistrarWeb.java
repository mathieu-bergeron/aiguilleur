package ca.ntro.core;



public interface ViewRegistrarWeb {

	void registerView(Class<? extends NtroView> viewClass, 
		          	  String lang, 
		          	  String htmlPath, 
		          	  String cssPath, 
		          	  String stringsPath,
			          Class<? extends NtroViewWeb> webViewClass);

}
