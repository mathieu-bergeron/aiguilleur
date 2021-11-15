package ca.ntro.core.wrappers.exception_catcher;

import ca.ntro.core.wrappers.result.Result;
import ca.ntro.core.wrappers.result.ResultNtro;

public class ExceptionCatcher {
	
	public static <R extends Object> Result<R> executeBlocking(Runner<R> task){
		
		ResultNtro<R> result = new ResultNtro<R>(null);
		
		try {

			result.registerValue(task.run());

		} catch(Throwable t) {
			
			result.registerException(t);
		}
		
		return result;
	}

}
