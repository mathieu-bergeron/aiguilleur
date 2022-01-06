package ca.ntro.app.models;

public interface ModelRegistrar {
	
	<M extends Model> void registerModelClass(Class<M> modelClass);

}
