package ca.ntro.jj.initialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ObjectId;
import ca.ntro.jj.services.Logger;
import ca.ntro.jj.services.LoggerJj;
import ca.ntro.jj.services.Tracer;
import ca.ntro.jj.services.TracerJj;
import ca.ntro.jj.task_graph.TaskGraph;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.tasks.generic.GenericAtomicTask;
import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.wrappers.future.Future;

public abstract class InitializerJj implements Initializer {
	
	private InitializerOptions options = new InitializerOptionsJj();
	
	private Map<ObjectId, Task> objectTasks = new HashMap<>();
	private Map<ClassId, Task> singletonTasks = new HashMap<>();
	
	private List<InitializedObject> initializedObjects(){

		List<InitializedObject> initializedObjects = new ArrayList<>();

		initializedObjects.add(new TracerJj());
		
		return initializedObjects;
	}

	private TaskGraph buildGraph() {
		
		TaskGraph graph;
		
		for(InitializedObject initializedObject : initializedObjects()) {
			
			Task thisObjectTask = new TaskJj();
			
			initializedObject.registerDependencies(new DependencyRegistrar() {
				@Override
				public void addDependency(ObjectId<? extends Object> objectId) {
					Task initializationTask = objectTasks.get(objectId);
					
					if(initializationTask == null) {
						initializationTask = initializationTask(objectId);
					}

					if(initializationTask == null) {
						throw new RuntimeException("[FATAL] Initialization failed for " + initializedObject.id());
					}

					thisObjectTask.addPreviousTask(initializationTask);
				}
				
				@Override
				public void addDependency(ClassId<? extends Object> classId) {
					Task initializationTask = singletonTasks.get(classId);
					
					if(initializationTask == null) {
						initializationTask = initializationTask(classId);
					}

					thisObjectTask.addPreviousTask(initializationTask);
				}
			});
			
			thisObjectTask.addEntryTask(new GenericAtomicTask() {

				run(ObjectMap objectMap){

					initializedObject.initialize(objectMap);
					
					// XXX: register new initialized object
					//      as next task might need it to progress
					objectMap.register(initializedObject.id(), initializedObject);
				}
			});
			
			graph.addSubTask(thisObjectTask);
		}

		return graph;
	}
	
	private Task initializationTask(ObjectId<? extends Object> objectId) {
		
		Task task = null;
		
		task = provideInitializationTask(objectId);
		
		return task;
	}

	private Task initializationTask(ClassId<? extends Object> classId) {
		
		Task task = null;

		if(classId.equals(Tracer.classId())) {

			task = TracerJj.initializationTask();
			
			
		}else if(classId.equals(Logger.classId())) {
			
			task = LoggerJj.initializationTask();

		}else {
			
			task = provideInitializationTask(classId);
			
		}
		
		return task;
	}
	
	
	protected abstract Task provideInitializationTask(ObjectId<? extends Object> objectId);
	protected abstract Task provideInitializationTask(ClassId<? extends Object> classId);
	
	private void initializeStaticImports(ObjectMap objectMap) {
		
		// XXX: place all static imports inside the same package
		//      as InitializerJj
		T.registerTracer(objectMap.getSingleton(Tracer.classId()));
	}

	@Override
	public Future<ObjectMap> execute() {
		
		Future<ObjectMap> future = buildGraph().execute();
		
		future.handleResult(objectMap -> {
			initializeStaticImports(objectMap);
		});
		
		return future;
	}

	@Override
	public ObjectMap executeBlocking() throws Throwable {
		
		ObjectMap objectMap = buildGraph().executeBlocking();
		
		initializeStaticImports(objectMap);
		
		return objectMap;
	}

	@Override
	public Initializer setOptions(InitializerOptions options) {
		this.options = options;

		return this;
	}
}
