package ca.ntro.core.wrappers.exception_catcher;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ExceptionCatcher {
	
	public static <R extends Object> Result<R> executeBlocking(Runner<R> task){
		
		ResultNtro<R> result = new ResultNtro<R>(null);
		
		try {
			
			Ntro.exceptionThrower().enterCatchingMode();

			result.registerValue(task.run());

			Ntro.exceptionThrower().exitCatchingMode();

		} catch(Throwable t) {
			
			result.registerException(t);
		}

		if(Ntro.exceptionThrower().hasException()) {
			
			result.registerException(Ntro.exceptionThrower().exception());
		}
		
		return result;
	}

}
