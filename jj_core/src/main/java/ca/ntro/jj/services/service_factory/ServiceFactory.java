package ca.ntro.jj.services.service_factory;

import java.util.List;

import ca.ntro.jj.services.Service;
import ca.ntro.jj.services.class_name.ClassNameService;
import ca.ntro.jj.services.factory.FactoryService;
import ca.ntro.jj.tasks.base.Task;
import ca.ntro.jj.tasks.task_graph.TaskGraphExecutable;

public class ServiceFactory {
	
	private FactoryService factoryService;
	private ClassNameService classNameService;

	private TaskGraphExecutable initializationGraph;
	
	public ServiceFactory(FactoryService factoryService, ClassNameService classNameService) {
		this.factoryService = factoryService;
		this.classNameService = classNameService;
	}

	public void addInitializationTask(ServiceDescriptor<?> serviceDescriptor, List<Class<? extends Service>> dependencies) {

		ServiceFactoryTask task = addTask(serviceDescriptor, dependencies);
		
		for(Class<? extends Service> dependency : dependencies) {
			

			
		}
	}

	private ServiceFactoryTask addTask(ServiceDescriptor<?> serviceDescriptor, List<Class<? extends Service>> dependencies) {

		String taskId = classNameService.simpleNameFor(serviceDescriptor.interfaceClass());
		ServiceFactoryTask task = new ServiceFactoryTask(factoryService, dependencies);
		
		initializationGraph.addTask(taskId, task);
		
		return task;
	}

	public Task initializationTaskFor(Class<? extends Service> serviceInterfaceClass) {

		String taskId = classNameService.simpleNameFor(serviceInterfaceClass);
		
		return initializationGraph.getTaskById(taskId);
	}
}
