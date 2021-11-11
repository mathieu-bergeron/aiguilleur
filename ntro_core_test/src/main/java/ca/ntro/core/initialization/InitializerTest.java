package ca.ntro.core.initialization;

import ca.ntro.core.services.ExceptionThrower;

public class InitializerTest {
	
	public static void registerExceptionThrower(ExceptionThrower exceptionThrower) {
		Ntro.registerExceptionThrower(exceptionThrower);
	}
	

}
