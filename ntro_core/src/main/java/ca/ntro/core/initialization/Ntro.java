package ca.ntro.core.initialization;

import ca.ntro.core.services.Asserter;
import ca.ntro.core.services.AsserterNull;
import ca.ntro.core.services.ExceptionThrower;
import ca.ntro.core.services.ExceptionThrowerNull;
import ca.ntro.core.services.ReflectionService;
import ca.ntro.core.services.ReflectionServiceNull;

public class Ntro {
	
	/* <ExceptionThrower> */
	
	private static ExceptionThrower exceptionThrower = new ExceptionThrowerNull();
	
	static void registerExceptionThrower(ExceptionThrower exceptionThrower){
		Ntro.exceptionThrower = exceptionThrower;
	}

	public static ExceptionThrower exceptionThrower(){
		return Ntro.exceptionThrower;
	}

	/* </ExceptionThrower> */
	
	
	
	
	/* <Asserter> */
	
	private static Asserter asserter = new AsserterNull();
	
	static void registerAsserter(Asserter asserter){
		Ntro.asserter = asserter;
	}

	public static Asserter asserter(){
		return Ntro.asserter;
	}

	/* </Asserter> */
	

	
	
	/* <ReflectionService> */
	
	private static ReflectionService reflectionService = new ReflectionServiceNull();
	
	static void registerReflectionService(ReflectionService reflectionService){
		Ntro.reflectionService = reflectionService;
	}

	public static ReflectionService reflectionService(){
		return Ntro.reflectionService;
	}

	/* </ReflectionService> */
	
}
