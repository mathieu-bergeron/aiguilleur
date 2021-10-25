package ca.ntro.jj.initialization;

import ca.ntro.jj.core.identifyers.ClassId;
import ca.ntro.jj.core.identifyers.ObjectId;

public interface DependencyRegistrar {

	void addDependency(ClassId<? extends Object> classId);
	void addDependency(ObjectId<? extends Object> objectId);

}
