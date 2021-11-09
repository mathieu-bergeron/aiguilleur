package ca.ntro.jj.initialization;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ObjectId;
import ca.ntro.jj.initialization.InitializerJj;
import ca.ntro.jj.services.FileOpener;
import ca.ntro.jj.services.FileOpenerJdk;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.tasks.generic.GenericAtomicTask;
import ca.ntro.jj.tasks.generic.GenericTask;
import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.wrappers.ExceptionHandler;
import ca.ntro.jj.wrappers.future.Future;

public class InitializerJdk extends InitializerJj {


	@Override
	protected Task provideInitializationTask(ObjectId<? extends Object> objectId) {

		Task task = null;
		
		return task;
	}

	@Override
	protected Task provideInitializationTask(ClassId<? extends Object> classId) {

		return null;
	}

}
