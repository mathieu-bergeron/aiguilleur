package ca.ntro.jj.services.service_factory;

import java.util.List;

import ca.ntro.jj.services.Service;
import ca.ntro.jj.services.class_name.ClassNameService;
import ca.ntro.jj.services.factory.FactoryService;
import ca.ntro.jj.tasks.TaskJj;

public class ServiceFactoryTask extends TaskJj {

	public ServiceFactoryTask(FactoryService factoryService, 
							  ClassNameService classNameService,
							  Class<? extends Service> serviceClass,
			                  List<Class<? extends Service>> dependencies) {
		
		ServiceFactoryAtomicTask exitTask = new ServiceFactoryAtomicTask(factoryService, 
				                                                         classNameService,
				                                                         serviceClass,
				                                                         dependencies);
		addExitTask(exitTask);
	}

}
