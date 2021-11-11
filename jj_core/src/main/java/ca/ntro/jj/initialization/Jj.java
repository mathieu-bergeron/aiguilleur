package ca.ntro.jj.initialization;

import ca.ntro.jj.services.ExceptionThrower;
import ca.ntro.jj.services.ExceptionThrowerNull;

public class Jj {
	
	private static ExceptionThrower exceptionThrower = new ExceptionThrowerNull();
	
	static void registerExceptionThrower(ExceptionThrower exceptionThrower){
		Jj.exceptionThrower = exceptionThrower;
	}

	public static ExceptionThrower exceptionThrower(){
		return Jj.exceptionThrower;
	}
}
