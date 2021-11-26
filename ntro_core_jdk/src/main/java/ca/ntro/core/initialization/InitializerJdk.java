package ca.ntro.core.initialization;

import ca.ntro.core.identifyers.ClassId;
import ca.ntro.core.identifyers.ObjectId;
import ca.ntro.core.initialization.InitializerNtro;
import ca.ntro.core.services.StackAnalyzerJdk;
import ca.ntro.core.tasks.base.Task;
import ca.ntro.core.values.ObjectMap;

public class InitializerJdk extends InitializerNtro {

	@Override
	public ObjectMap executeBlocking() throws Throwable {
		
		// FIXME!!!
		Ntro.registerStackAnalyzer(new StackAnalyzerJdk());
		
		
		return super.executeBlocking();
	}


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
