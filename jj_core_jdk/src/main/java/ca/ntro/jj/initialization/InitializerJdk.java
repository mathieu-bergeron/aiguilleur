package ca.ntro.jj.initialization;

import ca.ntro.jj.identifyers.ObjectIdJj;
import ca.ntro.jj.initialization.InitializerJj;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.values.ClassId;

public class InitializerJdk extends InitializerJj {


	@Override
	protected Task provideInitializationTask(ObjectIdJj objectId) {

		Task task = null;
		
		return task;
	}

	@Override
	protected Task provideInitializationTask(ClassId<? extends Object> classId) {

		return null;
	}

}
