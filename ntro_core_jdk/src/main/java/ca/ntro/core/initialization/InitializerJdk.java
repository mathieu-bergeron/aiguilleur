package ca.ntro.core.initialization;

import ca.ntro.core.identifyers.ClassId;
import ca.ntro.core.identifyers.ObjectId;
import ca.ntro.core.initialization.InitializerNtro;
import ca.ntro.core.services.StackAnalyzerJdk;
import ca.ntro.core.task_graphs.executable_task_graph.ExecutableTask;
import ca.ntro.core.task_graphs.task_graph.AtomicTask;
import ca.ntro.core.task_graphs.task_graph.AtomicTaskId;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.values.ObjectMap;

public class InitializerJdk extends InitializerNtro {

	@Override
	public ObjectMap executeBlocking() throws Throwable {
		
		// FIXME!!!
		Ntro.registerStackAnalyzer(new StackAnalyzerJdk());
		
		
		
		return super.executeBlocking();
	}


	@Override
	protected ExecutableTask provideInitializationTask(ObjectId objectId) {

		ExecutableTask task = null;
		
		return task;
	}

	@Override
	protected ExecutableTask provideInitializationTask(ClassId<? extends Object> classId) {

		return null;
	}



}
