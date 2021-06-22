package ca.ntro.jj.initialization;

import ca.ntro.jj.common.identifyiers.ClassId;
import ca.ntro.jj.common.identifyiers.ObjectId;

public interface DependencyRegistrar {

	void addDependency(ClassId<? extends Object> classId);
	void addDependency(ObjectId<? extends Object> objectId);

}
