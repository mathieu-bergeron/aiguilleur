package ca.ntro.jj.services.tracer;

public interface TracedCall {
	
	String getMethodName();
	String getFileName();
	int getLineNumber();

}
