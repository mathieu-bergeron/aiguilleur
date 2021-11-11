package ca.ntro.jj.initialization;

import ca.ntro.jj.services.Asserter;
import ca.ntro.jj.services.AsserterNull;
import ca.ntro.jj.services.ExceptionThrower;
import ca.ntro.jj.services.ExceptionThrowerNull;

public class Jj {
	
	/* <ExceptionThrower> */
	
	private static ExceptionThrower exceptionThrower = new ExceptionThrowerNull();
	
	static void registerExceptionThrower(ExceptionThrower exceptionThrower){
		Jj.exceptionThrower = exceptionThrower;
	}

	public static ExceptionThrower exceptionThrower(){
		return Jj.exceptionThrower;
	}

	/* </ExceptionThrower> */
	
	
	
	
	/* <Asserter> */
	
	private static Asserter asserter = new AsserterNull();
	
	static void registerAsserter(Asserter asserter){
		Jj.asserter = asserter;
	}

	public static Asserter asserter(){
		return Jj.asserter;
	}

	/* </Asserter> */
	
	
	
	
}
