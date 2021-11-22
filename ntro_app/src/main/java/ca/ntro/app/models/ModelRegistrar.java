package ca.ntro.app.models;

public interface ModelRegistrar {
	
	<M extends Model> void registerModel(Class<M> modelClass);

}
