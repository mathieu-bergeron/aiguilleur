package ca.ntro.jj.init;

import java.util.ArrayList;
import java.util.List;

import ca.jj.core.task_graph.TaskGraph;
import ca.ntro.jj.core.identifyers.ClassId;
import ca.ntro.jj.core.identifyers.ObjectId;
import ca.ntro.jj.core.services.TracerJj;
import ca.ntro.jj.initialization.DependencyRegistrar;
import ca.ntro.jj.initialization.InitializedObject;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.wrappers.future.Future;

public abstract class InitializerJj implements Initializer {
	
	private InitializerOptions options = new InitializerOptionsJj();

	private TaskGraph buildGraph() {
		
		List<InitializedObject> initializedObjects = new ArrayList<>();


		initializedObjects.add(new TracerJj());
		
		
		
		for(InitializedObject initializedObject : initializedObjects) {
			
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
