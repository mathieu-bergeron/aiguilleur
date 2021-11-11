package ca.ntro.core.initialization;

import ca.ntro.core.values.ObjectMap;

public interface SubTaskDependant {

	void registerSubTasks(SubTaskRegistrar registrar);
	void handleSubTaskResults(ObjectMap subTaskResults); 

}
