package ca.ntro.jj.server;

import ca.ntro.core.tasks.NtroTask;
import ca.ntro.core.tasks.NtroTaskAsync;
import ca.ntro.jj.services.LoggerNull;
import ca.ntro.jj.services.logger.Logger;

public abstract class ServerJj implements Server {

	private ServerOptions options;
	private Logger logger;

	// FIXME: no! We need a ServerFactory as 
	//        ServerJdk and ServerJSweet will not necessarily 
	//        have the same initializationTask
	public static NtroTask initializationTask(Class<? extends Server> serverClass,
											  Class<? extends Logger> loggerClass) {
		
		/* TODO: 
		 * 
		 * return a TaskGraph that 
		 * 
		 * - initializes every dependency
		 * - fetches the correct TaskResults
		 * - creates a ServerJj of type serverClass
		 * 
		 */
		
		NtroTask initializationTask = new NtroTaskAsync() {
			@Override
			protected void runTaskAsync() {
				
				// XXX: getResult from Logger init task
				
			}
			
			@Override
			protected void onFailure(Exception e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		initializationTask.addSubTask(Logger.initializationTask(loggerClass), "logger");
		
		return initializationTask;
	}


	public ServerJj(ServerOptions options) {

		this.logger = new LoggerNull();
		this.options = options;
	}

	public ServerJj(ServerOptions options, Logger logger) {
		logger.trace(this);
		
		this.options = options;
		this.logger = logger;
	}

	protected ServerOptions getOptions() {
		return options;
	}

	protected void setOptions(ServerOptions options) {
		this.options = options;
	}

	protected Logger getLogger() {
		return logger;
	}

	protected void setLogger(Logger logger) {
		this.logger = logger;
	}
}
