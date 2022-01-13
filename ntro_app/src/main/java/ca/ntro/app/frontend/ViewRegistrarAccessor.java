package ca.ntro.app.frontend;

public interface ViewRegistrarAccessor {
	
	<V extends View<?>> V view(Class<V> viewClass);

}
