package ca.ntro.jj.init;

import ca.jj.core.task_graph.TaskGraph;
import ca.ntro.jj.core.identifyers.ClassId;
import ca.ntro.jj.core.identifyers.ObjectId;
import ca.ntro.jj.core.services.TracerJj;
import ca.ntro.jj.initialization.DependencyRegistrar;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.wrappers.future.Future;

public abstract class InitializatorJj implements Initializator {
	
	private InitializationOptions options = new InitializationOptionsJj();

	private TaskGraph buildGraph() {
		
		
		TracerJj tracer = new TracerJj();
		
		/* According to TracerJj dependencies
		 * add a dependency to the initialization task
		 * provided by the Jdk or JSweet initializor
		 */
		tracer.registerDependencies(new DependencyRegistrar() {
			
			@Override
			public void addDependency(ObjectId<? extends Object> objectId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addDependency(ClassId<? extends Object> classId) {
				// TODO Auto-generated method stub
				
			}
		});
		
		return null;
	}
	
	protected abstract Task provideInitializationTask(ObjectId<? extends Object> objectId);
	protected abstract Task provideInitializationTask(ClassId<? extends Object> classId);

	@Override
	public Future<Void> execute() {
		return buildGraph().execute();
	}

	@Override
	public Future<Void> executeBlocking() {
		return buildGraph().executeBlocking();
	}

	@Override
	public Initializator setOptions(InitializationOptions options) {
		this.options = options;

		return this;
	}
}
