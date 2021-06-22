package ca.ntro.jj.initialization;


import ca.ntro.jj.common.values.ObjectMap;

public interface InitializedObject {

	void registerDependencies(DependencyRegistrar registrar);

	void initialize(ObjectMap resolvedDependencies);

}
