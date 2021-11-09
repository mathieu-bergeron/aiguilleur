package ca.ntro.jj.initialization;

import ca.ntro.jj.values.ObjectMap;

public interface SubTaskDependant {

	void registerSubTasks(SubTaskRegistrar registrar);
	void handleSubTaskResults(ObjectMap subTaskResults); 

}
