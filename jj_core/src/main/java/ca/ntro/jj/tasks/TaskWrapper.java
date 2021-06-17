package ca.ntro.jj.tasks;

public interface TaskWrapper {
	
	NtroTask getTask();
	GraphTraceConnector execute();

}
