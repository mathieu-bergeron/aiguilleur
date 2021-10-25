package ca.ntro.jj.initialization;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ObjectId;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.tasks.task_graph.TaskGraph;
import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.wrappers.future.Future;

public class InitializorJj implements Initializor {

	private static final InitializorJj instance = new InitializorJj();

	private InitializorJj() {
	}
	
	public static Initializor getInstance() {
		return instance;
	}

	private TaskGraph<? extends Task<?,?>> initializationGraph;

	@Override
	public <O> void registerSingleton(ClassId<O> classId, O object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void registerObject(ObjectId<O> objectId, O object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> void registerInitializationTask(ClassId<O> classId, InitializationTask initializationTask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <O> InitializationTask initializationTask(ClassId<O> classId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> Future<O> initializedSingleton(ClassId<O> classId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> Future<O> initializedObject(ObjectId<O> objectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Future<ObjectMap> initializedObjects() {
		return initializationGraph.execute();
	}

}
