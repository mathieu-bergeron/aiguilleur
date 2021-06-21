package ca.ntro.jj.services.service_factory;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.jj.services.Service;
import ca.ntro.jj.services.factory.FactoryService;
import ca.ntro.jj.tasks.base.AtomicTask;
import ca.ntro.jj.tasks.base.TaskCompleteNotifyier;
import ca.ntro.jj.tasks.results.AtomicTaskResult;
import ca.ntro.jj.tasks.results.AtomicTaskResultJj;
import ca.ntro.jj.tasks.results.TaskResults;
import ca.ntro.jj.wrappers.result.ExceptionHandler;

public class ServiceFactoryAtomicTask implements AtomicTask {
	
	private FactoryService factoryService;
	List<Class<? extends Service>> dependencies;
	private Throwable t;
	
	public ServiceFactoryAtomicTask(FactoryService factory, List<Class<? extends Service>> dependencies) {
		this.factoryService = factory;
		this.dependencies = dependencies;
	}

	@Override
	public void handleException(ExceptionHandler exceptionHandler) {
		if(t != null) {
			exceptionHandler.handle(t);
		}
	}

	@Override
	public AtomicTaskResult execute(TaskResults results, TaskCompleteNotifyier notifyier) {
		
		Service service = null;
		
		List<Object> resolvedDependencies = new ArrayList<>();
		for(Class<? extends Service> dependency : dependencies) {
			resolvedDependencies.add(results.getResult(dependency));
		}

		@SuppressWarnings("unchecked")
		Class<? extends Service> serviceClass = results.getResult(Class.class);
		
		try {
			
			service = factoryService.newInstance(serviceClass, resolvedDependencies);
			
		}catch(Throwable t) {

			this.t = t;

		}

		return new AtomicTaskResultJj(service);
	}

}
