package ca.ntro.core.services;

public interface ExceptionThrower {
	
	void throwException(Throwable t);

	void enterCatchingMode();
	void exitCatchingMode();

	boolean hasException();
	Throwable exception();

}
