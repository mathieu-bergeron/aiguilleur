package ca.ntro.core.initialization;

import ca.ntro.core.identifyers.ClassId;
import ca.ntro.core.identifyers.ObjectId;
import ca.ntro.core.initialization.InitializerJj;
import ca.ntro.core.tasks.base.Task;

public class InitializerJdk extends InitializerJj {


	@Override
	protected Task provideInitializationTask(ObjectId objectId) {

		Task task = null;
		
		return task;
	}

	@Override
	protected Task provideInitializationTask(ClassId<? extends Object> classId) {

		return null;
	}

}
