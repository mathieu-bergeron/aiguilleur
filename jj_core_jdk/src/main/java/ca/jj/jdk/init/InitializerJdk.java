package ca.jj.jdk.init;

import ca.ntro.jj.core.identifyers.ClassId;
import ca.ntro.jj.core.identifyers.ObjectId;
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
