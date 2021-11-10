package ca.ntro.jj.initialization;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ObjectId;
import ca.ntro.jj.initialization.InitializerJj;
import ca.ntro.jj.tasks.base.Task;

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
