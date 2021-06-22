package ca.ntro.jj.initialization;

import java.util.List;

import ca.ntro.jj.common.values.ObjectMap;

public interface InitializedObject {

	void registerDependencies(DependencyRegistrar registrar);

	void initialize(ObjectMap resolvedDependencies);

}
