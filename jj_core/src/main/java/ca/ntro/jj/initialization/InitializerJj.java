package ca.ntro.jj.initialization;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ObjectId;
import ca.ntro.jj.services.TracerJj;
import ca.ntro.jj.task_graph.TaskGraph;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.wrappers.future.Future;

public abstract class InitializerJj implements Initializer {
	
	private InitializerOptions options = new InitializerOptionsJj();
	
	private List<InitializedObject> initializedObjects(){

		List<InitializedObject> initializedObjects = new ArrayList<>();

		initializedObjects.add(new TracerJj());
		
		return initializedObjects;
	}

	private TaskGraph buildGraph() {
		
		
		for(InitializedObject initializedObject : initializedObjects()) {
			
			initializedObject.registerDependencies(new DependencyRegistrar() {
				@Override
				public void addDependency(ObjectId<? extends Object> objectId) {
					Task initializationTask = provideInitializationTask(objectId);
				}
				
				@Override
				public void addDependency(ClassId<? extends Object> classId) {
					Task initializationTask = provideInitializationTask(classId);
				}
			});
		}

		return null;
	}
	
	protected abstract Task provideInitializationTask(ObjectId<? extends Object> objectId);
	protected abstract Task provideInitializationTask(ClassId<? extends Object> classId);

	@Override
	public Future<Void> execute() {
		/* TODO: initialize static imports (T,Log, etc)
		 */
		return buildGraph().execute();
	}

	@Override
	public Future<Void> executeBlocking() {
		return buildGraph().executeBlocking();
	}

	@Override
	public Initializer setOptions(InitializerOptions options) {
		this.options = options;

		return this;
	}
}
