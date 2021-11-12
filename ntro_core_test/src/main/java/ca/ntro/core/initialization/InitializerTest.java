package ca.ntro.core.initialization;

import ca.ntro.core.services.ExceptionThrower;
import ca.ntro.core.services.ExceptionThrowerNtro;

public class InitializerTest {

	public static void initialize() {
		Ntro.registerExceptionThrower(new ExceptionThrowerNtro());
	}

	public static void registerExceptionThrower(ExceptionThrower exceptionThrower) {
		Ntro.registerExceptionThrower(exceptionThrower);
	}

	

}
