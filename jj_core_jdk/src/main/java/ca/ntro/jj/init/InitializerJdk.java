package ca.ntro.jj.init;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ObjectId;
import ca.ntro.jj.init.InitializerJj;
import ca.ntro.jj.tasks.base.Task;

public class InitializerJdk extends InitializerJj {


	@Override
	protected Task provideInitializationTask(ObjectId<? extends Object> objectId) {

		return null;
	}

	@Override
	protected Task provideInitializationTask(ClassId<? extends Object> classId) {

		return null;
	}

}
