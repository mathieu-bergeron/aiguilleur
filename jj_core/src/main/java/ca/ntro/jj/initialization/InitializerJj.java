package ca.ntro.jj.initialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.jj.identifyers.ClassId;
import ca.ntro.jj.identifyers.ObjectId;
import ca.ntro.jj.identifyers.ServiceId;
import ca.ntro.jj.services.TracerJj;
import ca.ntro.jj.task_graph.TaskGraph;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.values.ObjectMap;
import ca.ntro.jj.wrappers.future.Future;

public abstract class InitializerJj implements Initializer {
	
	private InitializerOptions options = new InitializerOptionsJj();
	
	private Map<ServiceId<?>, Task> serviceTasks = new HashMap<>();
	
	private List<Service<?>> services(){

		List<Service<?>> initializedObjects = new ArrayList<>();

		initializedObjects.add(new TracerJj());
		
		return initializedObjects;
	}

	private TaskGraph buildGraph() {
		
		TaskGraph graph = null;
		
		/*
		
		for(Service<?> service : services()) {
			
			Task thisObjectTask = new TaskJj();
			
			if(service instanceof ServiceDependant) {

				ServiceDependant serviceDepedant = (ServiceDependant) service;
				serviceDepedant.requestServices(serviceId -> {

					Task initializationTask = serviceTasks.get(serviceId);
					
					if(initializationTask == null) {
						initializationTask = createServiceTask(serviceId);
					}

					thisObjectTask.addPreviousTask(initializationTask);
				});
				
				thisObjectTask.addEntryTask(new GenericAtomicTask() {
					run(ObjectMap objectMap){
						((ServiceDependant) service).handleServices(objectMap);
					}
				});
				
			}
			
			if(service instanceof SubTaskDependant) {
				SubTaskDependant subTaskDependant = (SubTaskDependant) service;
				
				subTaskDependant.registerSubTasks((subTaskId, subTask) -> {
					thisObjectTask.addSubTask(subTask);
				});
				
				thisObjectTask.addExitTask(new GenericAtomicTask() {
					run(ObjectMap objectMap){
						((SubTaskDependant) service).handleSubTaskResults(subTaskResults);
					}
				});
			}
			
			graph.addSubTask(thisObjectTask);
		}
		
		*/

		return graph;
	}
	
	private Task createServiceTask(ServiceId<?> serviceId) {
		
		/*

		Task initializationTask = new TaskJj();
		

		initializationTask.addExitTask(new GenericAtomicTask() {

			run(ObjectMap objectMap){

				Class<?> serviceClass = serviceId._class();
				Service<?> serviceObj = (Service<?>) Factory.newInstance(serviceClass);
				
			}
		});
		
		return initializationTask;
		
		*/
		
		return null;
	}

	protected abstract Task provideInitializationTask(ObjectId objectId);
	protected abstract Task provideInitializationTask(ClassId<? extends Object> classId);
	
	private void initializeStaticImports(ObjectMap objectMap) {
		
		// XXX: place all static imports inside the same package
		//      as InitializerJj
		// T.registerTracer(objectMap.getSingleton(Tracer.serviceId()));
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
