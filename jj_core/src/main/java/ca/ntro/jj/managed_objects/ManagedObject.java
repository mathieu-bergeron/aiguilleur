package ca.ntro.jj.managed_objects;

import java.util.List;

import ca.ntro.jj.tasks.results.ObjectMap;

public interface ManagedObject {
	
	void registerDependencies(DependencyRegistrar registrar);

	void initialize(ObjectMap parameters);

}
