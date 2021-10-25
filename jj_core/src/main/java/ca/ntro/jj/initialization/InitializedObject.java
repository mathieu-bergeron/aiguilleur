package ca.ntro.jj.initialization;


import ca.ntro.jj.core.values.ObjectMap;

public interface InitializedObject {

	void registerDependencies(DependencyRegistrar registrar);

	void initialize(ObjectMap resolvedDependencies);

}
