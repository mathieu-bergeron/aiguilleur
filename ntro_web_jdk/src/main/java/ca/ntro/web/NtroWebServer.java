package ca.ntro.web;

import ca.ntro.core.Constants;
import ca.ntro.jdk.services.BackendServiceServer;
import ca.ntro.jj.services.EarlyInitialization;
import ca.ntro.jj.services.MessageService;
import ca.ntro.jj.services.ModelStore;
import ca.ntro.jj.services.NtroInitializationTask;
import ca.ntro.jj.services.RouterService;
import ca.ntro.jj.trace.__T;

public class NtroWebServer {

	public static NtroInitializationTask defaultInitializationTask(EarlyInitialization earlyInitialization,
																   Class<? extends BackendServiceServer> backendServiceClass, 
			                                                       Class<? extends ModelStore> modelStoreClass,
			                                                       Class<? extends MessageService> messageServiceClass,
			                                                       RouterService routerService) {
		__T.call(NtroWebServer.class, "defaultInitializationTask");

		earlyInitialization.performInitialization();

		
		NtroInitializationTask initializationTask = new NtroInitializationTask();
		initializationTask.setTaskId(Constants.INITIALIZATION_TASK_ID);

		initializationTask.addSubTask(new InitializationTaskWebserver(backendServiceClass, 
				                                                      modelStoreClass, 
				                                                      messageServiceClass, 
				                                                      routerService));
		return initializationTask;
	}

}
