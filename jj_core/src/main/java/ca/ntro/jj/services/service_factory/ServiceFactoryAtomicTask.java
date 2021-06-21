package ca.ntro.jj.services.service_factory;

import java.util.ArrayList;
import java.util.List;

import ca.ntro.jj.common.ExceptionDelayerJj;
import ca.ntro.jj.services.Service;
import ca.ntro.jj.services.class_name.ClassNameService;
import ca.ntro.jj.services.factory.FactoryService;
import ca.ntro.jj.tasks.base.AtomicTask;
import ca.ntro.jj.tasks.base.TaskCompleteNotifyier;
import ca.ntro.jj.tasks.results.AtomicTaskResultJj;
import ca.ntro.jj.tasks.results.NamedResults;

public class ServiceFactoryAtomicTask extends ExceptionDelayerJj<ServiceFactoryAtomicTask> 
                                      implements AtomicTask<ServiceFactoryAtomicTask> {
	
	private ClassNameService classNameService;
	private FactoryService factoryService;
	
	Class<? extends Service> serviceClass;
	List<Class<? extends Service>> dependencies;
	
	public ServiceFactoryAtomicTask(FactoryService factory, 
									ClassNameService classNameService,
									Class<? extends Service> serviceClass,
			                        List<Class<? extends Service>> dependencies) {
		
		
		this.serviceClass = serviceClass;
		this.classNameService = classNameService;
		this.factoryService = factory;
		this.dependencies = dependencies;
	}

	@Override
	public ServiceFactoryAtomicTask execute(NamedResults previousResults, TaskCompleteNotifyier notifyier) {
		
		Service service = null;
		
		List<Object> resolvedDependencies = new ArrayList<>();
		for(Class<? extends Service> dependency : dependencies) {
			String dependencyName = classNameService.simpleNameFor(dependency);
			resolvedDependencies.add(previousResults.getResult(dependency, dependencyName));
		}

		try {
			
			service = factoryService.newInstance(serviceClass, resolvedDependencies);
			
		}catch(Throwable t) {
			memorizeException(t);
		}

		notifyier.taskComplete(new AtomicTaskResultJj(service));
		
		return this;
	}
}
