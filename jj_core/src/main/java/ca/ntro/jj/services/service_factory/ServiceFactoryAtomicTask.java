package ca.ntro.jj.services.service_factory;

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
	private Throwable t;
	
	public ServiceFactoryAtomicTask(FactoryService factory) {
		this.factoryService = factory;
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

		@SuppressWarnings("unchecked")
		Class<? extends Service> serviceClass = results.getResult(Class.class);
		
		try {
			
			service = factoryService.newInstance(serviceClass);
			
		}catch(Throwable t) {

			this.t = t;

		}

		return new AtomicTaskResultJj(service);
	}

}
