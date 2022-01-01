package ca.ntro.core.initialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.identifyers.ClassId;
import ca.ntro.core.identifyers.ObjectId;
import ca.ntro.core.identifyers.ServiceId;
import ca.ntro.core.services.TracerNtro;
import ca.ntro.core.task_graphs.task_graph.Task;
import ca.ntro.core.task_graphs.task_graph.TaskGraph;
import ca.ntro.core.task_graphs.task_graph.TaskGraphNtro;
import ca.ntro.core.values.ObjectMap;
import ca.ntro.core.values.ObjectMapNtro;
import ca.ntro.core.wrappers.future.Future;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public abstract class InitializerNtro 

       extends        TaskGraphNtro

       implements Initializer {
	
	private InitializerOptions options = new InitializerOptionsNtro();
	
	private Map<ServiceId<?>, Task> serviceTasks = new HashMap<>();
	
	private List<Service<?>> services(){

		List<Service<?>> initializedObjects = new ArrayList<>();

		initializedObjects.add(new TracerNtro());
		
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
		
		future.handleValue(objectMap -> {
			initializeStaticImports(objectMap);
		});
		
		return future;
	}

	@Override
	public Result<ObjectMap> executeBlocking() {
		/*
		
		ObjectMap objectMap = buildGraph().executeBlocking();
		
		initializeStaticImports(objectMap);
		
		return objectMap;
		*/
		
		return new ResultNtro<>(new ObjectMapNtro());
	}

	@Override
	public Initializer setOptions(InitializerOptions options) {
		this.options = options;

		return this;
	}
}
