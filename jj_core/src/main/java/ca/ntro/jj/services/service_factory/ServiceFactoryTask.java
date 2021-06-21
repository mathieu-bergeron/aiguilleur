package ca.ntro.jj.services.service_factory;

import ca.ntro.jj.services.factory.FactoryService;
import ca.ntro.jj.tasks.TaskJj;

public class ServiceFactoryTask extends TaskJj {

	public ServiceFactoryTask(FactoryService factoryService) {
		
		ServiceFactoryAtomicTask exitTask = new ServiceFactoryAtomicTask(factoryService);
		
		addExitTask(exitTask);

	}

}
