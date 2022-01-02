package ca.ntro.core.initialization;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.identifyers.ClassId;
import ca.ntro.core.identifyers.ObjectId;
import ca.ntro.core.services.StackAnalyzerJdk;
import ca.ntro.core.stream.Stream;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.result.Result;

public class InitializerJdk extends InitializerNtro {

	@Override
	public Result<ObjectMap> executeBlocking() {
		
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
