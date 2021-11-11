package ca.ntro.jj.initialization;

import ca.ntro.jj.services.ExceptionThrower;

public class InitializerTest {
	
	public static void registerExceptionThrower(ExceptionThrower exceptionThrower) {
		Jj.registerExceptionThrower(exceptionThrower);
	}
	

}
