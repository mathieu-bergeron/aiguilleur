package ca.ntro.app.backend.handlers;


public interface BackendExecutor {
	
	void execute(BackendResults results, BackendNotifyer notifyer) throws Throwable;

}
