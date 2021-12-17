package ca.ntro.core.wrappers.exception_catcher;

import ca.ntro.core.initialization.Ntro;
import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ExceptionCatcher {
	
	public static <R extends Object> Result<R> executeBlocking(Runner<R> task){
		
		ResultNtro<R> result = new ResultNtro<R>(null);
		
		try {
			
			Ntro.exceptions().enterCatchingMode();

			result.registerValue(task.run());

			Ntro.exceptions().exitCatchingMode();

		} catch(Throwable t) {
			
			result.registerException(t);
		}

		if(Ntro.exceptions().hasException()) {
			
			result.registerException(Ntro.exceptions().exception());
		}
		
		return result;
	}

}
